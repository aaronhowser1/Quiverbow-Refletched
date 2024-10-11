package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class WeaponBase(
    val maxAmmo: Int
) : Item(
    Properties()
        .stacksTo(1)
        .component(ModDataComponents.AMMO_COUNT_COMPONENT, 0)
) {

    override fun getBarWidth(stack: ItemStack): Int {
        val currentAmmo = stack.get(ModDataComponents.AMMO_COUNT_COMPONENT) ?: 0

        return (currentAmmo / maxAmmo.toFloat() * 13).toInt()
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

}