package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.util.ClientUtil
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

abstract class GenericWeapon : HoldsAmmo, Item(
    Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.AMMO_COUNT_COMPONENT.get(),
            0
        )
) {

    override fun getBarWidth(stack: ItemStack): Int {
        return (getAmmo(stack) / maxAmmo.toFloat() * 13).toInt()
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: Item.TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val amount = if (ClientUtil.playerIsCreative()) "∞" else getAmmo(stack).toString()
        tooltipComponents.add(Component.literal("Ammo: $amount/$maxAmmo"))
    }

}