package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import com.mojang.blaze3d.vertex.PoseStack
import dev.aaronhowser.mods.quiverbowrefletched.entity.FrostLancerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class FrostLancerProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<FrostLancerProjectile>(context) {
    override fun getTextureLocation(p0: FrostLancerProjectile): ResourceLocation {
        return OtherUtil.modResource("textures/entity/projectile/cold_iron.png")
    }

    override fun render(entity: FrostLancerProjectile, entityYaw: Float, partialTicks: Float, poseStack: PoseStack, buffer: MultiBufferSource, packedLight: Int) {
        poseStack.scale(0.5f, 0.5f, 0.5f)
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight)
    }
}