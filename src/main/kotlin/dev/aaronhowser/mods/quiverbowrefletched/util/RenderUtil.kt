package dev.aaronhowser.mods.quiverbowrefletched.util

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth


/**
 * Grabbed largely from the [AoA source](https://github.com/Tslat/Advent-Of-Ascension/blob/1.21/source/util/RenderUtil.java)
 */
object RenderUtil {

    fun renderFullscreenTexture(guiGraphics: GuiGraphics, textureLocation: ResourceLocation) {
        RenderSystem.disableDepthTest()
        RenderSystem.depthMask(false)

        guiGraphics.blit(
            textureLocation,
            0,
            0,
            -90,
            0f,
            0f,
            guiGraphics.guiWidth(),
            guiGraphics.guiHeight(),
            guiGraphics.guiWidth(),
            guiGraphics.guiHeight()
        )

        RenderSystem.depthMask(true)
        RenderSystem.enableDepthTest()
    }

    fun renderCenteredTexture(
        guiGraphics: GuiGraphics,
        textureLocation: ResourceLocation,
        scale: Float
    ) {
        val minScreenDimension = minOf(guiGraphics.guiWidth(), guiGraphics.guiHeight())
        val scaledSize = minOf(
            guiGraphics.guiWidth() / minScreenDimension,
            guiGraphics.guiHeight() / minScreenDimension
        ) * scale

        val scaledWidth = Mth.floor(minScreenDimension * scaledSize)
        val scaledHeight = Mth.floor(minScreenDimension * scaledSize)

        val offsetX = (guiGraphics.guiWidth() - scaledWidth) / 2
        val offsetY = (guiGraphics.guiHeight() - scaledHeight) / 2

        val rightEdge = offsetX + scaledWidth
        val bottomEdge = offsetY + scaledHeight

        RenderSystem.enableBlend()
        guiGraphics.blit(
            textureLocation,
            offsetX,
            offsetY,
            -90,
            0f,
            0f,
            scaledWidth,
            scaledHeight,
            scaledWidth,
            scaledHeight
        )

        // Fill borders with black

        guiGraphics.fill(RenderType.guiOverlay(), 0, bottomEdge, guiGraphics.guiWidth(), guiGraphics.guiHeight(), -90, -16777216)
        guiGraphics.fill(RenderType.guiOverlay(), 0, 0, guiGraphics.guiWidth(), offsetY, -90,  -16777216)
        guiGraphics.fill(RenderType.guiOverlay(), 0, offsetY, offsetX, bottomEdge, -90,  -16777216)
        guiGraphics.fill(RenderType.guiOverlay(), rightEdge, offsetY, guiGraphics.guiWidth(), bottomEdge, -90,  -16777216)
    }

    fun renderTexture(
        pGuiGraphics: GuiGraphics,
        textureLocation: ResourceLocation,
        x: Int, y: Int,
        width: Int, height: Int
    ) {
        RenderSystem.disableDepthTest()
        RenderSystem.depthMask(false)

        pGuiGraphics.blit(
            textureLocation,
            x,
            y,
            -90,
            0f,
            0f,
            width,
            height,
            width,
            height
        )

        RenderSystem.depthMask(true)
        RenderSystem.enableDepthTest()
    }

}