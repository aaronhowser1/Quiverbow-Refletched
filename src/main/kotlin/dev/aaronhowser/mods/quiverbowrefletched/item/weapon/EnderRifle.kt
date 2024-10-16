package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.ClientUtil
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent

class EnderRifle : BasicAmmoHoldingItem(
    maxAmmo = 8
) {

    companion object {
        fun checkShouldZoom(event: ComputeFovModifierEvent) {
            val player = event.player
            if (!player.isCrouching) return

            val mainHandIsEnderRifle = player.mainHandItem.`is`(ModItems.ENDER_RIFLE)
            val offHandIsEnderRifle = player.offhandItem.`is`(ModItems.ENDER_RIFLE)
            if (!mainHandIsEnderRifle && !offHandIsEnderRifle) return

            event.newFovModifier /= 7.5f
        }

        fun checkShouldLowerMouseSensitivity(event: CalculatePlayerTurnEvent) {
            val player = ClientUtil.localPlayer ?: return
            if (!player.isCrouching) return

            val mainHandIsEnderRifle = player.mainHandItem.`is`(ModItems.ENDER_RIFLE)
            val offHandIsEnderRifle = player.offhandItem.`is`(ModItems.ENDER_RIFLE)
            if (!mainHandIsEnderRifle && !offHandIsEnderRifle) return

            event.mouseSensitivity /= 1.5f
            event.cinematicCameraEnabled = true
        }

    }

}