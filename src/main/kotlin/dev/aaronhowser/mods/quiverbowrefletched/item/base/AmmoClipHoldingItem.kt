package dev.aaronhowser.mods.quiverbowrefletched.item.base


import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class AmmoClipHoldingItem(
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.AMMO_CLIP_COMPONENT.get(),
            ItemStack.EMPTY
        )
) : Item(properties) {

    companion object {
        fun getClip(stack: ItemStack): ItemStack {
            return stack.get(ModDataComponents.AMMO_CLIP_COMPONENT.get()) ?: ItemStack.EMPTY
        }

        fun getClipAmmo(stack: ItemStack): Int {
            val clipStack = getClip(stack)

            return clipStack.get(ModDataComponents.AMMO_COUNT_COMPONENT.get()) ?: -1
        }

        fun consumeClipAmmo(stack: ItemStack, amount: Int): Boolean {
            val clipStack = getClip(stack)
            val currentAmount = clipStack.get(ModDataComponents.AMMO_COUNT_COMPONENT.get()) ?: return false
            if (currentAmount < amount) return false

            val newAmount = currentAmount - amount
            clipStack.set(ModDataComponents.AMMO_COUNT_COMPONENT.get(), newAmount)
            stack.set(ModDataComponents.AMMO_CLIP_COMPONENT.get(), clipStack)
            return true
        }
    }

    override fun getBarWidth(stack: ItemStack): Int {
        val clipStack = getClip(stack)
        val clipItem = clipStack.item
        if (clipItem !is AmmoHoldingItem) return 0

        val maxAmmo = clipItem.maxAmmo

        return (AmmoHoldingItem.getAmmo(clipStack) / maxAmmo.toFloat() * 13).toInt()
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

}