package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.world.item.ItemStack

interface HoldsAmmo {

    val maxAmmo: Int

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

}