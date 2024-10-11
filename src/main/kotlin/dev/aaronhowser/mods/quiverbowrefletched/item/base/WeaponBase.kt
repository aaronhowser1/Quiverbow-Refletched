package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class WeaponBase(
    val maxAmmo: Int
) : Item(
    Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.AMMO_COUNT_COMPONENT.get(),
            0
        )
) {

    fun getAmmo(stack: ItemStack): Int {
        return stack.get(ModDataComponents.AMMO_COUNT_COMPONENT.get()) ?: 0
    }

    fun consumeAmmo(stack: ItemStack, amount: Int): Boolean {
        val currentAmount = getAmmo(stack)
        if (currentAmount < amount) return false

        val newAmount = currentAmount - amount
        stack.set(ModDataComponents.AMMO_COUNT_COMPONENT.get(), newAmount)
        return true
    }

    override fun getBarWidth(stack: ItemStack): Int {
        return (getAmmo(stack) / maxAmmo.toFloat() * 13).toInt()
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

}