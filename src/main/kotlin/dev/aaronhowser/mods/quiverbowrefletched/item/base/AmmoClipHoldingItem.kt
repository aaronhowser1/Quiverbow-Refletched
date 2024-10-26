package dev.aaronhowser.mods.quiverbowrefletched.item.base


import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.BasicAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

abstract class AmmoClipHoldingItem(
    private val clipItem: BasicAmmoClipItem,
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
            ItemStackListComponent(1)
        )
) : Item(properties) {

    companion object {
        fun getClip(stack: ItemStack): ItemStack {
            return stack.get(ModDataComponents.ADVANCED_AMMO_COMPONENT.get())?.stacks?.firstOrNull() ?: ItemStack.EMPTY
        }

        fun getClipAmmo(stack: ItemStack): Int {
            val clipStack = getClip(stack)

            return clipStack.get(ModDataComponents.BASIC_AMMO_COMPONENT.get()) ?: -1
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
            return consumeClipAmmo(stack, amount)
        }

        protected fun consumeClipAmmo(stack: ItemStack, amount: Int): Boolean {
            val clipStack = getClip(stack)
            val currentAmount = clipStack.get(ModDataComponents.BASIC_AMMO_COMPONENT.get()) ?: return false
            if (currentAmount < amount) return false

            val newAmount = currentAmount - amount
            clipStack.set(ModDataComponents.BASIC_AMMO_COMPONENT.get(), newAmount)
            stack.set(ModDataComponents.ADVANCED_AMMO_COMPONENT.get(), ItemStackListComponent(clipStack))
            return true
        }

        fun ejectClip(stack: ItemStack, player: Player) {
            val clipStack = getClip(stack)
            if (clipStack.isEmpty) return

            if (!player.addItem(clipStack)) {
                player.drop(clipStack, false)
            }
            stack.set(ModDataComponents.ADVANCED_AMMO_COMPONENT.get(), ItemStackListComponent(1))
        }
    }

    fun insertClip(gunStack: ItemStack, clipStack: ItemStack): Boolean {
        if (clipStack.item != this.clipItem) return false

        val currentClip = getClip(gunStack)
        if (!currentClip.isEmpty) return false

        gunStack.set(ModDataComponents.ADVANCED_AMMO_COMPONENT.get(), ItemStackListComponent(clipStack.copy()))
        clipStack.shrink(1)
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

        if (!insertClip(thisStack, otherStack)) return false

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

        val clipStack = getClip(thisStack)
        if (clipStack.isEmpty) return false

        val otherStack = slot.item
        if (!otherStack.isEmpty) return false

        slot.set(clipStack.copy())
        thisStack.set(ModDataComponents.ADVANCED_AMMO_COMPONENT.get(), ItemStackListComponent(1))

        return true
    }

    override fun getBarWidth(stack: ItemStack): Int {
        val clipStack = getClip(stack)
        val clipItem = clipStack.item
        if (clipItem !is BasicAmmoHoldingItem) return 0

        val maxAmmo = clipItem.maxAmmo

        return (BasicAmmoHoldingItem.getAmmoCount(clipStack) / maxAmmo.toFloat() * 13).toInt()
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
        val clipAmmoCount = getClipAmmo(stack)

        tooltipComponents.add(Component.literal("Clip Ammo: $clipAmmoCount"))
    }

}