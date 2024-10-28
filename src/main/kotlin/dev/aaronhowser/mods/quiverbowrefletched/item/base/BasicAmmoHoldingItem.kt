package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.util.ClientUtil
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

/**
 * Knows only how much ammo it holds, but doesn't know what form that ammo takes.
 */
abstract class BasicAmmoHoldingItem(
    val maxAmmo: Int,
    private val barColor: Int = 0x00FF00,
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.BASIC_AMMO_COMPONENT.get(),
            0
        )
) : Item(properties) {

    companion object {
        fun getMaxAmmo(stack: ItemStack): Int {
            return (stack.item as? BasicAmmoHoldingItem)?.maxAmmo ?: 0
        }

        fun getAmmoCount(stack: ItemStack): Int {
            return stack.getOrDefault(ModDataComponents.BASIC_AMMO_COMPONENT.get(), 0)
        }

        fun setAmmoCount(stack: ItemStack, amount: Int) {
            stack.set(ModDataComponents.BASIC_AMMO_COMPONENT.get(), amount)
        }

        /**
         * @return true if the entity successfully uses the ammo, either because they have infinite or the ammo was used
         */
        @JvmStatic
        protected fun tryEntityUse(
            livingEntity: LivingEntity,
            stack: ItemStack,
            amount: Int = 1
        ): Boolean {
            require(amount > 0) { "Amount must be a positive number" }
            if (livingEntity.hasInfiniteMaterials()) return true
            return modifyAmmoCount(stack, -amount)
        }

        fun modifyAmmoCount(stack: ItemStack, amount: Int): Boolean {
            val currentAmount = getAmmoCount(stack)
            val newAmount = currentAmount + amount

            if (newAmount !in 0..getMaxAmmo(stack)) return false

            setAmmoCount(stack, newAmount)
            return true
        }
    }

    override fun getBarWidth(stack: ItemStack): Int {
        return (getAmmoCount(stack) / maxAmmo.toFloat() * 13).toInt()
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

    override fun getBarColor(stack: ItemStack): Int {
        return barColor
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)

        val amount = if (ClientUtil.playerIsCreative()) "∞" else getAmmoCount(stack).toString()
        tooltipComponents.add(Component.literal("Ammo: $amount/$maxAmmo"))
    }

}