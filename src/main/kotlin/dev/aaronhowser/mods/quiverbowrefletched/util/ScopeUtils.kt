package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
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

    private fun eitherHandIs(player: Player, item: Item) = player.mainHandItem.`is`(item) || player.offhandItem.`is`(item)

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


        val zoomLevel = if (eitherHandIs(player, ModItems.ENDER_RIFLE.get())) {
            ServerConfig.ENDER_BOW_ZOOM_FACTOR.get().toFloat()
        } else if (eitherHandIs(player, ModItems.FROST_LANCER.get())) {
            ServerConfig.FROST_LANCER_ZOOM_FACTOR.get().toFloat()
        } else if (eitherHandIs(player, ModItems.ENDER_BOW.get())) {
            ServerConfig.ENDER_BOW_ZOOM_FACTOR.get().toFloat()
        } else {
            isScoped = false
            zoomFactor = 1f
            return
        }

        isScoped = true
        zoomFactor = zoomLevel

        RenderUtil.renderCenteredTexture(
            guiGraphics,
            scopeTexture,
            1f
        )

        //TODO: Render eyes of ender in the border around the overlay

    }

    private val scopeTexture = OtherUtil.modResource("textures/overlay/scope.png")

}