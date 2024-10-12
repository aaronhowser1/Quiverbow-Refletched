package dev.aaronhowser.mods.quiverbowrefletched.item

import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoHoldingItem
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class AmmoClipItem(
    maxAmmo: Int,
    val ammoPredicate: ItemPredicate
) : AmmoHoldingItem(maxAmmo = maxAmmo) {

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (!player.isSecondaryUseActive) return InteractionResultHolder.pass(usedStack)

        if (!level.isClientSide) {
            for (stack in player.inventory.items) {
                if (!ammoPredicate.test(stack)) continue

                val currentAmmo = getAmmo(usedStack)
                if (currentAmmo >= maxAmmo) break

                val stackSize = stack.count

                val remainingAmmo = maxAmmo - currentAmmo
                val amountToInsert = minOf(remainingAmmo, stackSize)

                stack.shrink(amountToInsert)
                modifyAmmoCount(usedStack, amountToInsert)

                level.playSound(
                    null,
                    player,
                    SoundEvents.ITEM_PICKUP,
                    SoundSource.PLAYERS,
                    1f,
                    0.33f
                )
            }
        }

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val amount = getAmmo(stack)
        tooltipComponents.add(Component.literal("Ammo: $amount/$maxAmmo"))
    }

}