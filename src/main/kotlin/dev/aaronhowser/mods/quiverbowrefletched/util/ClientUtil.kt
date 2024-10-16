package dev.aaronhowser.mods.quiverbowrefletched.util

import net.minecraft.client.Minecraft
import net.minecraft.client.Options
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer

object ClientUtil {

    val localPlayer: LocalPlayer?
        get() = Minecraft.getInstance().player

    val localLevel: ClientLevel?
        get() = Minecraft.getInstance().level

    val options: Options
        get() = Minecraft.getInstance().options

    fun playerIsCreative(): Boolean = localPlayer?.isCreative ?: false

}