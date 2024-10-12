package dev.aaronhowser.mods.quiverbowrefletched.item

import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoHoldingItem
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class AmmoClipItem(
    maxAmmo: Int,
    val ammoPredicate: ItemPredicate
) : AmmoHoldingItem(maxAmmo = maxAmmo) {

    companion object {
        fun getAmmoPredicate(stack: ItemStack): ItemPredicate {
            return if (stack.item is AmmoClipItem) {
                (stack.item as AmmoClipItem).ammoPredicate
            } else {
                ItemPredicate.Builder.item().of().build()
            }
        }
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