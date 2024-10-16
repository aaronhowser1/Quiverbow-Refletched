package dev.aaronhowser.mods.quiverbowrefletched.util

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation


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