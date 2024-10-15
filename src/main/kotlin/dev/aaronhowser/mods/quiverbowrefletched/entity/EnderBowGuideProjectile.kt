package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class EnderBowGuideProjectile(
    entityType: EntityType<EnderBowGuideProjectile>,
    level: Level
) : AbstractArrow(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.ENDER_BOW_GUIDE_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.discard()

        if (this.level().getBlockState(result.blockPos).block != Blocks.TARGET) return

        //TODO: Make this clientside
        val owner = this.owner as? Player ?: return
        level().playSound(
            null,
            owner.x,
            owner.eyeY,
            owner.z,
            SoundEvents.ARROW_HIT_PLAYER,
            SoundSource.PLAYERS,
            0.18f,
            0.45f * 2
        )
    }

    override fun getDefaultPickupItem(): ItemStack {
        return ItemStack.EMPTY
    }

    override fun onHitEntity(result: EntityHitResult) {
        super.discard()

        //TODO: Make this clientside
        val owner = this.owner as? Player ?: return
        level().playSound(
            null,
            owner.x,
            owner.eyeY,
            owner.z,
            SoundEvents.ARROW_HIT_PLAYER,
            SoundSource.PLAYERS,
            0.18f,
            0.45f * 2
        )
    }

}