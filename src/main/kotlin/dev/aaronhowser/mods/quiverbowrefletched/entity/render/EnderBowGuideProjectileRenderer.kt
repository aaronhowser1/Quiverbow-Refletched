package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderBowGuideProjectile
import dev.aaronhowser.mods.quiverbowrefletched.util.ClientUtil
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.client.renderer.culling.Frustum
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class EnderBowGuideProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<EnderBowGuideProjectile>(context) {

    override fun getTextureLocation(p0: EnderBowGuideProjectile): ResourceLocation {
        return OtherUtil.modResource("textures/entity/projectile/ender.png")
    }

    override fun shouldRender(livingEntity: EnderBowGuideProjectile, camera: Frustum, camX: Double, camY: Double, camZ: Double): Boolean {
        val owner = livingEntity.owner
        if (owner != ClientUtil.localPlayer) return false

        return super.shouldRender(livingEntity, camera, camX, camY, camZ)
    }

}