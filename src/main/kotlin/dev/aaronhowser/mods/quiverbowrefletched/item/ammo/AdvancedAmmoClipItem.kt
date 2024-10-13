package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.common.Tags

class AdvancedAmmoClipItem(
    maxAmmo: Int,
    private val barColor: Int,
    private val allowedAmmoTag: TagKey<Item>,
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
            ItemStackListComponent(maxAmmo)
        )
) : Item(properties) {

    companion object {
        val SEED = AdvancedAmmoClipItem(512, 0x00FF00, Tags.Items.SEEDS)

        fun setAmmo(clipStack: ItemStack, ammoStacks: List<ItemStack>) {
            clipStack.set(
                ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
                ItemStackListComponent(
                    getMaxAmmoAmount(clipStack),
                    ammoStacks
                )
            )
        }

        fun getAmmoComponent(stack: ItemStack): ItemStackListComponent? {
            return stack.get(ModDataComponents.ADVANCED_AMMO_COMPONENT.get())
        }

        fun getMaxAmmoAmount(stack: ItemStack): Int {
            return getAmmoComponent(stack)?.maxAmount ?: -1
        }

        fun getAmmoStacks(stack: ItemStack): List<ItemStack> {
            return getAmmoComponent(stack)?.stacks ?: emptyList()
        }

        fun getAmmoCount(stack: ItemStack): Int {
            return getAmmoComponent(stack)?.getTotalAmount() ?: -1
        }

    }

    private fun insertAmmo(thisStack: ItemStack, otherStack: ItemStack): Boolean {
        if (!otherStack.`is`(allowedAmmoTag)) return false

        val maxAmmo = getMaxAmmoAmount(thisStack)
        if (maxAmmo <= 0) return false

        val currentAmmo = getAmmoCount(thisStack)
        if (currentAmmo >= maxAmmo) return false

        //TODO: Make sure this doesn't allow exceeding the max amount

        val currentAmmoStacks = getAmmoStacks(thisStack)
        val newAmmoStacks = OtherUtil.flattenStacks(currentAmmoStacks + otherStack)

        setAmmo(thisStack, newAmmoStacks)
        return true
    }

    override fun overrideOtherStackedOnMe(
        thisStack: ItemStack,
        otherStack: ItemStack,
        slot: Slot,
        action: ClickAction,
        player: Player,
        access: SlotAccess
    ): Boolean {
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false

        if (!insertAmmo(thisStack, otherStack)) return false

        //TODO: Custom reload sound?
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

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

    override fun getBarWidth(stack: ItemStack): Int {
        return (getAmmoCount(stack) / getMaxAmmoAmount(stack).toFloat() * 13).toInt()
    }

    override fun getBarColor(stack: ItemStack): Int {
        return barColor
    }

}