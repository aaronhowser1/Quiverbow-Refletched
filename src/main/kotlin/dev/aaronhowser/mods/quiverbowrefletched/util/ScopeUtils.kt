package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphics
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent
import net.neoforged.neoforge.client.event.RenderHandEvent

object ScopeUtils {

    private var isScoped = false
    private var zoomFactor = 1f

    fun tryZoom(event: ComputeFovModifierEvent) {
        if (!isScoped) return
        event.newFovModifier *= zoomFactor
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
            zoomFactor = 1f
            return
        }

        val player = ClientUtil.localPlayer ?: return

        if (!player.isCrouching) {
            isScoped = false
            zoomFactor = 1f
            return
        }

        val mainHandIsEnderRifle = player.mainHandItem.`is`(ModItems.ENDER_RIFLE)
        val offHandIsEnderRifle = player.offhandItem.`is`(ModItems.ENDER_RIFLE)
        if (!mainHandIsEnderRifle && !offHandIsEnderRifle) {
            isScoped = false
            zoomFactor = 1f
            return
        }

        isScoped = true
        zoomFactor = ServerConfig.ENDER_BOW_ZOOM_FACTOR.get().toFloat()     //TODO: Make this depend on what scoped item is being held

        RenderUtil.renderCenteredTexture(
            guiGraphics,
            scopeTexture,
            1f
        )

        //TODO: Render eyes of ender in the border around the overlay

    }

    private val scopeTexture = OtherUtil.modResource("textures/overlay/scope.png")

}