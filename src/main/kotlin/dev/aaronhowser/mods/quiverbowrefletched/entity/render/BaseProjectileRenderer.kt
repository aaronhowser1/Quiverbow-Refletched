package dev.aaronhowser.mods.quiverbowrefletched.entity.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.util.Mth
import net.minecraft.world.entity.projectile.Projectile

abstract class BaseProjectileRenderer<E : Projectile>(
    context: EntityRendererProvider.Context
) : EntityRenderer<E>(context) {

    override fun render(
        entity: E,
        entityYaw: Float,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        poseStack.pushPose()

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, entity.yRotO, entity.yRot) - 90.0f))
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.xRot)))

        poseStack.mulPose(Axis.XP.rotationDegrees(45.0f))
        poseStack.scale(0.05625f, 0.05625f, 0.05625f)
        poseStack.translate(-4.0f, 0.0f, 0.0f)

        val vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity)))
        val lastPose = poseStack.last()

        this.vertex(lastPose, vertexConsumer, -7, -2, -2, 0.0f, 0.15625f, -1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, 2, -2, 0.0f, 0.3125f, -1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, 2, -2, 0.0f, 0.15625f, 1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, packedLight)
        this.vertex(lastPose, vertexConsumer, -7, -2, -2, 0.0f, 0.3125f, 1, 0, 0, packedLight)

        for (j in 0..3) {
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0f))
            this.vertex(lastPose, vertexConsumer, -8, -2, 0, 0.0f, 0.0f, 0, 1, 0, packedLight)
            this.vertex(lastPose, vertexConsumer, 8, -2, 0, 0.5f, 0.0f, 0, 1, 0, packedLight)
            this.vertex(lastPose, vertexConsumer, 8, 2, 0, 0.5f, 0.15625f, 0, 1, 0, packedLight)
            this.vertex(lastPose, vertexConsumer, -8, 2, 0, 0.0f, 0.15625f, 0, 1, 0, packedLight)
        }

        poseStack.popPose()
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }

    fun vertex(
        pose: PoseStack.Pose,
        consumer: VertexConsumer,
        x: Int,
        y: Int,
        z: Int,
        u: Float,
        v: Float,
        normalX: Int,
        normalY: Int,
        normalZ: Int,
        packedLight: Int
    ) {
        consumer.addVertex(pose, x.toFloat(), y.toFloat(), z.toFloat()).setColor(-1).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, normalX.toFloat(), normalZ.toFloat(), normalY.toFloat())
    }

}