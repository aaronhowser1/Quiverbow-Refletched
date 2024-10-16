package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent

object ScopeUtils {

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