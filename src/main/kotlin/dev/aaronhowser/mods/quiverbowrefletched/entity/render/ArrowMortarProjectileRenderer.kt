package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import dev.aaronhowser.mods.quiverbowrefletched.entity.ArrowMortarProjectile
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class ArrowMortarProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<ArrowMortarProjectile>(context) {

    //TODO: Custom texture?
    override fun getTextureLocation(p0: ArrowMortarProjectile): ResourceLocation {
        return ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png")
    }

}