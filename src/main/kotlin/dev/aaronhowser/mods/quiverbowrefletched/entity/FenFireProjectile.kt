package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModBlocks
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class FenFireProjectile(
    entityType: EntityType<FenFireProjectile>,
    level: Level
) : ThrowableItemProjectile(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.FEN_FIRE_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun onHitEntity(result: EntityHitResult) {
        val hitEntity = result.entity as? LivingEntity ?: return

        hitEntity.addEffect(
            MobEffectInstance(
                MobEffects.GLOWING,
                10 * 20,
                0,
                false,
                false
            )
        )
    }

    override fun onHitBlock(result: BlockHitResult) {
        val posHit = result.blockPos
        val sideHit = result.direction

        val posNext = posHit.relative(sideHit)
        val blockNext = level().getBlockState(posNext)

        if (blockNext.canBeReplaced()) {
            level().setBlockAndUpdate(
                posNext,
                ModBlocks.FEN_FIRE_BLOCK.get()
                    .defaultBlockState()
                    .setValue(DirectionalBlock.FACING, sideHit.opposite)
            )
        }

        this.discard()
    }

    override fun getDefaultItem(): Item {
        return Items.GLOWSTONE
    }
}