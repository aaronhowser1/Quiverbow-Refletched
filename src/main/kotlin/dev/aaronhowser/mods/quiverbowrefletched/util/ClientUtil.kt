package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent

object ClientUtil {

    val localPlayer: LocalPlayer?
        get() = Minecraft.getInstance().player

    val localLevel: ClientLevel?
        get() = Minecraft.getInstance().level

    fun playerIsCreative(): Boolean = localPlayer?.isCreative ?: false

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