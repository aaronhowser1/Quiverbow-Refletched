package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class EnderRifleRoundProjectile(
    entityType: EntityType<EnderRifleRoundProjectile>,
    level: Level
) : AbstractArrow(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.ENDER_RIFLE_ROUND_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun isNoGravity(): Boolean {
        return true
    }

    //TODO: Maybe increase scale with time? Maybe add a trail effect?

    override fun onHitEntity(result: EntityHitResult) {
        val hitEntity = result.entity as? LivingEntity ?: return

        val damageSource = this.damageSources().thrown(this, owner)

        // TODO: Update the damage value
        hitEntity.hurt(damageSource, 4f + (this.tickCount * 0.1f))

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