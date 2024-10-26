package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import dev.aaronhowser.mods.quiverbowrefletched.entity.FrostLancerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class FrostLancerProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<FrostLancerProjectile>(context) {
    override fun getTextureLocation(p0: FrostLancerProjectile): ResourceLocation {
        return OtherUtil.modResource("textures/entity/projectile/cold_iron.png")
    }
}