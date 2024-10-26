package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import com.mojang.blaze3d.vertex.PoseStack
import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderRifleProjectile
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation

class EnderRifleProjectileRenderer(
    context: EntityRendererProvider.Context
) : ArrowRenderer<EnderRifleProjectile>(context) {
    override fun getTextureLocation(p0: EnderRifleProjectile): ResourceLocation {
        return OtherUtil.modResource("textures/entity/projectile/ender.png")
    }

    override fun render(entity: EnderRifleProjectile, entityYaw: Float, partialTick: Float, poseStack: PoseStack, bufferSource: MultiBufferSource, packedLight: Int) {
        poseStack.scale(0.5f, 0.5f, 0.5f)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}