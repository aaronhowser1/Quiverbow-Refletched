package dev.aaronhowser.mods.quiverbowrefletched.event

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.entity.render.EnderBowGuideProjectileRenderer
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.registries.DeferredHolder

@EventBusSubscriber(
    modid = QuiverBowRefletched.ID,
    bus = EventBusSubscriber.Bus.MOD,
    value = [Dist.CLIENT]
)
object ClientModBusEvents {

    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent) {

        fun <T : Entity> registerEntityRenderer(
            entityHolder: DeferredHolder<EntityType<*>, EntityType<T>>,
            provider: EntityRendererProvider<T>
        ) {
            EntityRenderers.register(entityHolder.get(), provider)
        }

        registerEntityRenderer(ModEntityTypes.ENDER_BOW_GUIDE_PROJECTILE, ::EnderBowGuideProjectileRenderer)

    }

}