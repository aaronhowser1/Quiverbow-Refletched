package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class BasicAmmoClipItem(
    maxAmmo: Int,
    private val ammoItem: Item,
    barColor: Int
) : BasicAmmoHoldingItem(maxAmmo, barColor) {

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (!player.isSecondaryUseActive) return InteractionResultHolder.pass(usedStack)

        if (!level.isClientSide) {
            for (iteratedStack in player.inventory.items) {
                if (!iteratedStack.`is`(ammoItem)) continue

                while (iteratedStack.count > 0 && getAmmoCount(usedStack) < maxAmmo) {
                    iteratedStack.shrink(1)
                    modifyAmmoCount(usedStack, +1)
                }

                level.playSound(
                    null,
                    player,
                    SoundEvents.ITEM_PICKUP,
                    SoundSource.PLAYERS,
                    1f,
                    0.33f
                )
            }
        }

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

    override fun overrideOtherStackedOnMe(
        thisStack: ItemStack,
        otherStack: ItemStack,
        slot: Slot,
        action: ClickAction,
        player: Player,
        access: SlotAccess
    ): Boolean {
        if (thisStack.count != 1) return false
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false
        if (getAmmoCount(thisStack) >= maxAmmo) return false
        if (!otherStack.`is`(ammoItem)) return false

        val amount = otherStack.count
        val amountToInsert = minOf(maxAmmo - getAmmoCount(thisStack), amount)

        modifyAmmoCount(thisStack, amountToInsert)
        otherStack.shrink(amountToInsert)

        //TODO: Custom sound?
        player.level().playSound(
            null,
            player.blockPosition(),
            SoundEvents.ITEM_PICKUP,
            SoundSource.PLAYERS,
            1f,
            0.33f
        )

        return true
    }

    override fun overrideStackedOnOther(
        thisStack: ItemStack,
        slot: Slot,
        action: ClickAction,
        player: Player
    ): Boolean {
        if (thisStack.count != 1) return false
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false

        val myAmmo = getAmmoCount(thisStack)
        if (myAmmo <= 0) return false

        val thatStack = slot.item

        if (thatStack.isEmpty) {
            val amountToPlace = minOf(myAmmo, ammoItem.defaultInstance.maxStackSize)
            val newStack = ItemStack(ammoItem, amountToPlace)

            slot.set(newStack)
            modifyAmmoCount(thisStack, -amountToPlace)
        } else if (thatStack.`is`(ammoItem)) {
            val amountThatCanFit = thatStack.maxStackSize - thatStack.count
            val amountToPlace = minOf(amountThatCanFit, myAmmo)

            if (amountToPlace <= 0) return false

            thatStack.grow(amountToPlace)
            modifyAmmoCount(thisStack, -amountToPlace)
        } else return false

        return true
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val amount = getAmmoCount(stack)
        tooltipComponents.add(Component.literal("Ammo: $amount/$maxAmmo"))
    }


    companion object {
        val SUGAR = BasicAmmoClipItem(200, Items.SUGAR_CANE, 0x00FF00)
        val OBSIDIAN = BasicAmmoClipItem(16, Items.OBSIDIAN, 0x666666)
        val GOLD = BasicAmmoClipItem(72, Items.GOLD_NUGGET, 0xFFD700)
        val THORN = BasicAmmoClipItem(64, Items.CACTUS, 0x00FF00)
        val LAPIS = BasicAmmoClipItem(150, Items.LAPIS_LAZULI, 0x0000FF)
        val REDSTONE = BasicAmmoClipItem(64, Items.REDSTONE, 0xFF0000)
        val LARGE_NETHERRACK = BasicAmmoClipItem(200, Items.NETHERRACK, 0x800000)
        val LARGE_REDSTONE = BasicAmmoClipItem(200, Items.REDSTONE_BLOCK, 0xFF0000)
        val ENDER_QUARTZ = BasicAmmoClipItem(8, Items.QUARTZ, 0xFFFFFF)
    }

}