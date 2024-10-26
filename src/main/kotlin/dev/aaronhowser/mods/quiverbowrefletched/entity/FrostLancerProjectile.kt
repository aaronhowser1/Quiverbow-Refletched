package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import kotlin.random.Random

class FrostLancerProjectile(
    entityType: EntityType<FrostLancerProjectile>,
    level: Level
) : AbstractArrow(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.FROST_LANCER_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun getDefaultGravity(): Double {
        return super.getDefaultGravity() / 2
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)
        this.discard()
    }

    override fun onHitEntity(result: EntityHitResult) {
        this.discard()

        val hitEntity = result.entity as? LivingEntity ?: return
        val damageSource = this.damageSources().thrown(this, owner)

        val damage = Random.nextDouble(
            from = ServerConfig.FROST_LANCER_DAMAGE_MINIMUM.get(),
            until = ServerConfig.FROST_LANCER_DAMAGE_MAXIMUM.get()
        ).toFloat()

        hitEntity.hurtTime = 0

        hitEntity.hurt(damageSource, damage)

        val nauseaDuration = ServerConfig.FROST_LANCER_NAUSEA_DURATION.get()
        if (nauseaDuration > 0) {
            hitEntity.addEffect(
                MobEffectInstance(
                    MobEffects.CONFUSION,
                    nauseaDuration
                )
            )
        }

        val slownessDuration = ServerConfig.FROST_LANCER_SLOWNESS_DURATION.get()
        if (slownessDuration > 0) {
            hitEntity.addEffect(
                MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN,
                    slownessDuration,
                    ServerConfig.FROST_LANCER_SLOWNESS_STRENGTH.get() - 1
                )
            )
        }

    }

    override fun getDefaultPickupItem(): ItemStack {
        return ItemStack.EMPTY
    }
}