package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.entity.SilkenSpinnerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class SilkenSpinner : BasicAmmoHoldingItem(
    maxAmmo = 8,
    barColor = 0x999999
) {

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (!entityUse(player, usedStack)) {
            return InteractionResultHolder.fail(usedStack)
        }

        val projectile = SilkenSpinnerProjectile(player)
        level.addFreshEntity(projectile)
        projectile.shootFromRotation(
            player,
            player.xRot,
            player.yRot,
            0.0f,
            1.5f,
            1.0f
        )

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

}