package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.entity.AquaAcceleratorProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BucketPickup
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.Fluids

class AquaAccelerator : BasicAmmoHoldingItem(
    maxAmmo = 16,
    barColor = 0x0000FF
) {

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (tryRefillFromWaterSource(level, player, usedStack)) {
            return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
        }

        if (!entityUse(player, usedStack)) {
            return InteractionResultHolder.fail(usedStack)
        }

        val projectile = AquaAcceleratorProjectile(player)
        level.addFreshEntity(projectile)
        projectile.shootFromRotation(
            player,
            player.xRot,
            player.yRot,
            0.0f,
            ServerConfig.AQUA_ACCELERATOR_PROJECTILE_SPEED.get().toFloat(),
            1.0f
        )

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

    //TODO: Allow refilling from water tanks?
    private fun tryRefillFromWaterSource(level: Level, player: Player, usedStack: ItemStack): Boolean {
        if (player.isSecondaryUseActive) return false

        val canRefill = getAmmoCount(usedStack) < getMaxAmmo(usedStack)
        if (!canRefill) return false

        val blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY)
        val blockPos = blockHitResult.blockPos
        val fluidState = level.getFluidState(blockPos)

        if (!fluidState.isSourceOfType(Fluids.WATER)) return false

        val blockState = level.getBlockState(blockPos)
        val bucketPickup = blockState.block as? BucketPickup ?: return false

        bucketPickup.pickupBlock(player, level, blockPos, blockState)
        bucketPickup.getPickupSound(blockState).ifPresent { soundEvent ->
            player.playSound(soundEvent, 1.0f, 1.0f)
        }
        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos)

        return modifyAmmoCount(usedStack, 1)
    }

}