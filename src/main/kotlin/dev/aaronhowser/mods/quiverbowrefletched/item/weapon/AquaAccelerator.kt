package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

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

        if (tryRefill(level, player, usedStack)) {
            return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
        }

        if (getAmmo(usedStack) <= 0) {
            return InteractionResultHolder.fail(usedStack)
        }

        val projectile = AquaAcceleratorProjectile(player)
        level.addFreshEntity(projectile)
        projectile.shootFromRotation(
            player,
            player.xRot,
            player.yRot,
            0.0f,
            1.5f,
            1.0f
        )

        setAmmo(
            usedStack,
            getAmmo(usedStack) - 1
        )

        return super.use(level, player, usedHand)
    }

    //TODO: Allow refilling from water tanks?
    private fun tryRefill(level: Level, player: Player, usedStack: ItemStack): Boolean {
        if (player.isSecondaryUseActive) return false

        val canRefill = getAmmo(usedStack) < getMaxAmmo(usedStack)
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

        setAmmo(
            usedStack,
            getAmmo(usedStack) + 1
        )

        return true
    }

}