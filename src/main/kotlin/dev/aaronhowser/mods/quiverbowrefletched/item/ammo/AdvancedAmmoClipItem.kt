package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.neoforged.neoforge.common.Tags

/**
 * Stores the ItemStacks of ammo it holds.
 */
open class AdvancedAmmoClipItem(
    protected val maxAmmo: Int,
    private val barColor: Int = 0x00FF00,
    private val ammoPredicate: (ItemStack) -> Boolean,
    properties: Properties = getDefaultProperties(maxAmmo)
) : Item(properties) {

    constructor(
        maxAmmo: Int,
        barColor: Int = 0x00FF00,
        allowedAmmoTag: TagKey<Item>,
        properties: Properties = getDefaultProperties(maxAmmo)
    ) : this(maxAmmo, barColor, { it.`is`(allowedAmmoTag) }, properties)

    companion object {

        fun getDefaultProperties(maxAmmo: Int): Properties =
            Properties()
                .stacksTo(1)
                .component(
                    ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
                    ItemStackListComponent(maxAmmo)
                )

        val SEED_JAR = AdvancedAmmoClipItem(maxAmmo = 512, allowedAmmoTag = Tags.Items.SEEDS)

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

    protected open fun insertAmmo(
        thisStack: ItemStack,
        otherStack: ItemStack,
        player: Player
    ): Boolean {
        if (!ammoPredicate(otherStack)) return false

        val maxAmmo = getMaxAmmoAmount(thisStack)
        if (maxAmmo <= 0) return false

        val currentAmmo = getAmmoCount(thisStack)
        if (currentAmmo >= maxAmmo) return false

        val amountToInsert = minOf(maxAmmo - currentAmmo, otherStack.count)

        val currentAmmoStacks = getAmmoStacks(thisStack)
        val newAmmoStacks = OtherUtil.flattenStacks(currentAmmoStacks + otherStack)

        setAmmo(thisStack, newAmmoStacks)
        otherStack.shrink(amountToInsert)
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

        if (!insertAmmo(thisStack, otherStack, player)) return false

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

    override fun overrideStackedOnOther(
        thisStack: ItemStack,
        slot: Slot,
        action: ClickAction,
        player: Player
    ): Boolean {
        if (thisStack.count != 1) return false
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false

        val ammoStacks = getAmmoStacks(thisStack)
        if (ammoStacks.isEmpty()) return false

        val otherStack = slot.item
        if (otherStack.isEmpty) {
            val ammoStack = ammoStacks.lastOrNull() ?: return false

            slot.set(ammoStack.copy())
            setAmmo(thisStack, ammoStacks.dropLast(1))

            return true
        }

        if (ammoStacks.any { ItemStack.isSameItemSameComponents(it, otherStack) }) {
            val lastMatchingStack = ammoStacks.lastOrNull { ItemStack.isSameItemSameComponents(it, otherStack) }
            val stackToPlace = lastMatchingStack?.copy() ?: return false

            val amountThatCanFit = otherStack.maxStackSize - otherStack.count
            val amountToPlace = minOf(amountThatCanFit, stackToPlace.count)

            if (amountToPlace <= 0) return false

            otherStack.grow(amountToPlace)
            stackToPlace.shrink(amountToPlace)

            val newAmmoStacks = OtherUtil.flattenStacks(
                if (stackToPlace.isEmpty) {
                    ammoStacks - lastMatchingStack
                } else {
                    ammoStacks - lastMatchingStack + stackToPlace
                }
            )

            setAmmo(thisStack, newAmmoStacks)

            return true
        }

        return false
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

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)

        val ammoCount = getAmmoCount(stack)
        val maxAmmo = getMaxAmmoAmount(stack)

        tooltipComponents.add(
            Component.literal("$ammoCount / $maxAmmo")
        )

        if (!tooltipFlag.hasShiftDown()) {
            tooltipComponents.add(
                Component.literal("Hold SHIFT for more info")
            )
        } else {
            for (ammoStack in getAmmoStacks(stack)) {
                tooltipComponents.add(
                    Component
                        .literal("• ${ammoStack.count}x ")
                        .append(ammoStack.displayName)
                )
            }
        }

    }

}