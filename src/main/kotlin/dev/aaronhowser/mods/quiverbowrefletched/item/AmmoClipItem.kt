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