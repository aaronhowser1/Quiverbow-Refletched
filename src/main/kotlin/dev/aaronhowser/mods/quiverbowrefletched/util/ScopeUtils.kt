package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphics
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent
import net.neoforged.neoforge.client.event.RenderHandEvent

object ScopeUtils {

    private var isScoped = false

    fun tryZoom(event: ComputeFovModifierEvent) {
        if (!isScoped) return
        event.newFovModifier /= 7.5f
    }

    fun tryLowerSensitivity(event: CalculatePlayerTurnEvent) {
        if (!isScoped) return

        event.mouseSensitivity /= 1.5f
        event.cinematicCameraEnabled = true
    }

    fun tryHideHand(event: RenderHandEvent) {
        if (!isScoped) return

        event.isCanceled = true
    }

    fun renderScope(guiGraphics: GuiGraphics, deltaTracker: DeltaTracker) {
        if (!ClientUtil.options.cameraType.isFirstPerson) {
            isScoped = false
            return
        }

        val player = ClientUtil.localPlayer ?: return

        if (!player.isCrouching) {
            isScoped = false
            return
        }

        val mainHandIsEnderRifle = player.mainHandItem.`is`(ModItems.ENDER_RIFLE)
        val offHandIsEnderRifle = player.offhandItem.`is`(ModItems.ENDER_RIFLE)
        if (!mainHandIsEnderRifle && !offHandIsEnderRifle) {
            isScoped = false
            return
        }

        isScoped = true

        RenderUtil.renderCenteredTexture(
            guiGraphics,
            scopeTexture,
            1f
        )

        //TODO: Render eyes of ender in the border around the overlay

    }

    private val scopeTexture = OtherUtil.modResource("textures/overlay/scope.png")

}