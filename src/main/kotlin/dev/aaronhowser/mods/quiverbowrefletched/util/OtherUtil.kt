package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import net.minecraft.resources.ResourceLocation

object OtherUtil {

    fun modResource(path: String): ResourceLocation =
        ResourceLocation.fromNamespaceAndPath(QuiverBowRefletched.ID, path)

    val Boolean?.isTrue: Boolean
        get() = this == true

}