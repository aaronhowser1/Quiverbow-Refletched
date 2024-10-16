package dev.aaronhowser.mods.quiverbowrefletched.util

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.GameRenderer
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

    fun renderScaledCustomSizedTexture(
        matrixStack: PoseStack,
        x: Float,
        y: Float,
        u: Float,
        v: Float,
        uWidth: Float,
        vHeight: Float,
        renderWidth: Float,
        renderHeight: Float,
        textureWidth: Float,
        textureHeight: Float
    ) {
        val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX)
        val pose = matrixStack.last().pose()

        val widthRatio = 1 / textureWidth
        val heightRatio = 1 / textureHeight

        RenderSystem.setShader(GameRenderer::getPositionTexShader)

        buffer.addVertex(
            pose,
            x,
            y + renderHeight,
            0f
        ).setUv(
            u * widthRatio,
            (v + vHeight) * heightRatio
        )

        buffer.addVertex(
            pose,
            x + renderWidth,
            y + renderHeight,
            0f
        ).setUv(
            (u + uWidth) * widthRatio,
            (v + vHeight) * heightRatio
        )

        buffer.addVertex(
            pose,
            x + renderWidth,
            y,
            0f
        ).setUv(
            (u + uWidth) * widthRatio,
            v * heightRatio
        )

        buffer.addVertex(
            pose,
            x,
            y,
            0f
        ).setUv(
            u * widthRatio,
            v * heightRatio
        )

        BufferUploader.drawWithShader(buffer.buildOrThrow())
    }

}