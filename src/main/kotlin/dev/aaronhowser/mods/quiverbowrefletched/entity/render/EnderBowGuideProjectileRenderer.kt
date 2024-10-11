package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderBowGuideProjectile
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class EnderBowGuideProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<EnderBowGuideProjectile>(context) {

    override fun getTextureLocation(p0: EnderBowGuideProjectile): ResourceLocation {
        return ResourceLocation.withDefaultNamespace("textures/entity/projectiles/spectral_arrow.png")
    }

}