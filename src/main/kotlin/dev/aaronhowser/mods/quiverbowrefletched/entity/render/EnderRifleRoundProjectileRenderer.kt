package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderRifleRoundProjectile
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class EnderRifleRoundProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<EnderRifleRoundProjectile>(context) {
    override fun getTextureLocation(p0: EnderRifleRoundProjectile): ResourceLocation {
        return OtherUtil.modResource("textures/entity/projectile/ender.png")
    }
}