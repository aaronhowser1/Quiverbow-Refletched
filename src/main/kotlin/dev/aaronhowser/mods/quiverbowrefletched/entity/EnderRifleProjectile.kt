package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class EnderRifleProjectile(
    entityType: EntityType<EnderRifleProjectile>,
    level: Level
) : AbstractArrow(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.ENDER_RIFLE_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun getDefaultGravity(): Double {
        return super.getDefaultGravity() / 2
    }

    //TODO: Maybe increase scale with time? Maybe add a trail effect?

    override fun onHitEntity(result: EntityHitResult) {
        val hitEntity = result.entity as? LivingEntity ?: return
        val damageSource = this.damageSources().thrown(this, owner)

        val damage =
            minOf(
                ServerConfig.ENDER_RIFLE_DAMAGE_MINIMUM.get()
                        + (this.tickCount * ServerConfig.ENDER_RIFLE_DAMAGE_INCREASE_PER_TICK.get()),
                ServerConfig.ENDER_RIFLE_DAMAGE_MAXIMUM.get()
            )

        hitEntity.hurt(damageSource, damage.toFloat())
        hitEntity.knockback(
            ServerConfig.ENDER_RIFLE_KNOCKBACK.get(),
            hitEntity.x - this.x,
            hitEntity.z - this.z
        )

        this.discard()
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)
        this.discard()
    }

    override fun getDefaultPickupItem(): ItemStack {
        return Items.IRON_INGOT.defaultInstance
    }

}