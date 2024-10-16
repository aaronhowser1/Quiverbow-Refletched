package dev.aaronhowser.mods.quiverbowrefletched.util

import com.mojang.blaze3d.systems.RenderSystem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.client.CameraType
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
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

    fun renderScope(guiGraphics: GuiGraphics, deltaTracker: DeltaTracker) {
        if (ClientUtil.options.cameraType != CameraType.FIRST_PERSON) return
        val player = ClientUtil.localPlayer ?: return
        if (!player.isCrouching) return

        RenderSystem.enableBlend()
        ClientUtil.renderFullscreenTexture(
            guiGraphics,
            ResourceLocation.withDefaultNamespace("textures/misc/spyglass_scope.png")
        )

    }

}