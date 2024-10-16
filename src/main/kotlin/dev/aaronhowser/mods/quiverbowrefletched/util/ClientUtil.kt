package dev.aaronhowser.mods.quiverbowrefletched.util

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.Minecraft
import net.minecraft.client.Options
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.minecraft.resources.ResourceLocation

object ClientUtil {

    val localPlayer: LocalPlayer?
        get() = Minecraft.getInstance().player

    val localLevel: ClientLevel?
        get() = Minecraft.getInstance().level

    val options: Options
        get() = Minecraft.getInstance().options

    fun playerIsCreative(): Boolean = localPlayer?.isCreative ?: false

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

}