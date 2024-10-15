package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class SilkenSpinnerProjectile(
    entityType: EntityType<SilkenSpinnerProjectile>,
    level: Level
) : ThrowableItemProjectile(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.SILKEN_SPINNER_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun onHitEntity(result: EntityHitResult) {
        val hitEntity = result.entity as? LivingEntity ?: return

        val effectAdded = hitEntity.addEffect(
            MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                20 * 15,    // Duration
                3,          // Amplifier
                false,      // Ambient
                true         // Show particles
            )
        )

        if (!effectAdded) return

        hitEntity.hurtMarked = true

        this.discard()
        val owner = this.owner ?: return

        level().playSound(
            null,
            owner.x,
            owner.y,
            owner.z,
            SoundEvents.SLIME_SQUISH_SMALL,     //TODO: Change sound?
            SoundSource.PLAYERS,
            0.45f,
            0.45f * 2
        )
        level().playSound(
            null,
            owner.x,
            owner.eyeY,
            owner.z,
            SoundEvents.ARROW_HIT_PLAYER,
            SoundSource.PLAYERS,
            0.025f,
            0.45f * 2
        )
    }

    //TODO: Custom cobweb block that disappears over time?
    override fun onHitBlock(result: BlockHitResult) {
        val posHit = result.blockPos
        val sideHit = result.direction

        val posNext = posHit.relative(sideHit)
        val blockNext = level().getBlockState(posNext)

        if (blockNext.canBeReplaced()) {
            level().setBlockAndUpdate(posNext, Blocks.COBWEB.defaultBlockState())
        } else {
            val cobwebDrop = ItemEntity(
                level(),
                posNext.x.toDouble() + 0.5,
                posNext.y.toDouble() + 0.5,
                posNext.z.toDouble() + 0.5,
                Items.COBWEB.defaultInstance
            )

            level().addFreshEntity(cobwebDrop)
        }
    }

    override fun getDefaultItem(): Item {
        return Items.COBWEB
    }

}