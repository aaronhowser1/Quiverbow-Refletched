package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

class FlintDuster : BasicAmmoHoldingItem(
    maxAmmo = 256,
    barColor = 0x555555
) {

    companion object {
        private const val AMMO_PER_FLINT_BOX = 32
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
        if (getAmmo(thisStack) >= maxAmmo) return false
        if (otherStack.item != ModItems.BOX_FLINT_DUST.get()) return false

        //TODO: Make sure I can insert if I have <32 missing ammo

        val otherStackSize = otherStack.count
        val amountNeededForFullAmmo = (maxAmmo - getAmmo(thisStack)) / AMMO_PER_FLINT_BOX
        val amountToInsert = minOf(amountNeededForFullAmmo, otherStackSize)

        modifyAmmoCount(
            thisStack,
            (amountToInsert * AMMO_PER_FLINT_BOX).coerceAtMost(maxAmmo)
        )
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

}