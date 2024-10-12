package dev.aaronhowser.mods.quiverbowrefletched.item.base


import dev.aaronhowser.mods.quiverbowrefletched.item.AmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

abstract class AmmoClipHoldingItem(
    val clipItem: AmmoClipItem,
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.AMMO_CLIP_CONTENTS.get(),
            ItemStackListComponent(1)
        )
) : Item(properties) {

    companion object {
        fun getClip(stack: ItemStack): ItemStack {
            return stack.get(ModDataComponents.AMMO_CLIP_CONTENTS.get())?.items?.firstOrNull() ?: ItemStack.EMPTY
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
            stack.set(ModDataComponents.AMMO_CLIP_CONTENTS.get(), ItemStackListComponent(clipStack))
            return true
        }

        fun ejectClip(stack: ItemStack, player: Player) {
            val clipStack = getClip(stack)
            if (clipStack.isEmpty) return

            if (!player.addItem(clipStack)) {
                player.drop(clipStack, false)
            }
            stack.set(ModDataComponents.AMMO_CLIP_CONTENTS.get(), ItemStackListComponent(1))
        }
    }

    fun insertClip(gunStack: ItemStack, clipStack: ItemStack): Boolean {
        if (clipStack.isEmpty) return false
        if (clipStack.item != this.clipItem) return false

        val currentClip = getClip(gunStack)
        if (!currentClip.isEmpty) return false

        gunStack.set(ModDataComponents.AMMO_CLIP_CONTENTS.get(), ItemStackListComponent(clipStack))

        return true
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)
        if (player.isSecondaryUseActive && !level.isClientSide) ejectClip(usedStack, player)

        return super.use(level, player, usedHand)
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