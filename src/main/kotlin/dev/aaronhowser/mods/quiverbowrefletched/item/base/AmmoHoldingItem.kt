package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.util.ClientUtil
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

abstract class AmmoHoldingItem(
    val maxAmmo: Int,
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.AMMO_COUNT_COMPONENT.get(),
            0
        )
) : Item(properties) {

    companion object {
        fun getAmmo(stack: ItemStack): Int {
            return stack.getOrDefault(ModDataComponents.AMMO_COUNT_COMPONENT.get(), 0)
        }

        fun getMaxAmmo(stack: ItemStack): Int {
            return (stack.item as? AmmoHoldingItem)?.maxAmmo ?: 0
        }

        fun setAmmo(stack: ItemStack, amount: Int) {
            stack.set(ModDataComponents.AMMO_COUNT_COMPONENT.get(), amount)
        }

        fun consumeAmmo(stack: ItemStack, amount: Int): Boolean {
            val currentAmount = getAmmo(stack)
            if (currentAmount < amount) return false

            val newAmount = currentAmount - amount
            setAmmo(stack, newAmount)
            return true
        }

        fun addAmmo(stack: ItemStack, amount: Int): Boolean {
            val currentAmount = getAmmo(stack)
            if (currentAmount + amount > getMaxAmmo(stack)) return false

            val newAmount = currentAmount + amount
            setAmmo(stack, newAmount)
            return true
        }
    }

    override fun getBarWidth(stack: ItemStack): Int {
        return (getAmmo(stack) / maxAmmo.toFloat() * 13).toInt()
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)

        val amount = if (ClientUtil.playerIsCreative()) "∞" else getAmmo(stack).toString()
        tooltipComponents.add(Component.literal("Ammo: $amount/$maxAmmo"))
    }

}