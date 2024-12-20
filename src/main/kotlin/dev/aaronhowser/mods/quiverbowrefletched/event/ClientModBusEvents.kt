package dev.aaronhowser.mods.quiverbowrefletched.event

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.datagen.model.ModItemModelProvider
import dev.aaronhowser.mods.quiverbowrefletched.entity.render.ArrowMortarProjectileRenderer
import dev.aaronhowser.mods.quiverbowrefletched.entity.render.EnderBowGuideProjectileRenderer
import dev.aaronhowser.mods.quiverbowrefletched.entity.render.EnderRifleProjectileRenderer
import dev.aaronhowser.mods.quiverbowrefletched.entity.render.FrostLancerProjectileRenderer
import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.AdvancedAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoClipHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import dev.aaronhowser.mods.quiverbowrefletched.util.ScopeUtils
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.event.ModelEvent
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredItem

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
        registerEntityRenderer(ModEntityTypes.ARROW_MORTAR_PROJECTILE, ::ArrowMortarProjectileRenderer)
        registerEntityRenderer(ModEntityTypes.ENDER_RIFLE_PROJECTILE, ::EnderRifleProjectileRenderer)
        registerEntityRenderer(ModEntityTypes.FROST_LANCER_PROJECTILE, ::FrostLancerProjectileRenderer)

        registerEntityRenderer(ModEntityTypes.AQUA_ACCELERATOR_PROJECTILE, ::ThrownItemRenderer)
        registerEntityRenderer(ModEntityTypes.SILKEN_SPINNER_PROJECTILE, ::ThrownItemRenderer)
        registerEntityRenderer(ModEntityTypes.FEN_FIRE_PROJECTILE, ::ThrownItemRenderer)
        registerEntityRenderer(ModEntityTypes.SNOW_CANNON_PROJECTILE, ::ThrownItemRenderer)
        registerEntityRenderer(ModEntityTypes.COIN_TOSSER_PROJECTILE, ::ThrownItemRenderer)
    }

    @SubscribeEvent
    fun onModelRegistry(event: ModelEvent.RegisterAdditional) {

        val pullPredicateItems = listOf(
            ModItems.ENDER_BOW,
            ModItems.BOW_WITH_QUIVER
        )

        for (item in pullPredicateItems) {
            ItemProperties.register(
                item.get(),
                ModItemModelProvider.pullAmount
            ) { usedStack, _, entity, _ ->
                if (entity == null) return@register 0.0f

                return@register (usedStack.getUseDuration(entity) - entity.useItemRemainingTicks).toFloat() / 20.0f
            }

            ItemProperties.register(
                item.get(),
                ModItemModelProvider.isPulling
            ) { usedStack, _, entity, _ ->
                if (entity != null && entity.isUsingItem && entity.useItem === usedStack) 1f else 0f
            }
        }

        val possiblyEmptyBasicAmmoItems: List<DeferredItem<out BasicAmmoHoldingItem>> = listOf(
            // Weapons
            ModItems.AQUA_ACCELERATOR,

            // Ammo
            ModItems.GOLD_MAGAZINE,
            ModItems.LARGE_NETHERRACK_MAGAZINE,
            ModItems.LARGE_REDSTONE_MAGAZINE,
            ModItems.OBSIDIAN_MAGAZINE,
            ModItems.REDSTONE_MAGAZINE,
            ModItems.SUGAR_ROD_CLIP,
            ModItems.THORN_MAGAZINE,
            ModItems.ENDER_QUARTZ_CLIP
        )

        for (item in possiblyEmptyBasicAmmoItems) {
            ItemProperties.register(
                item.get(),
                ModItemModelProvider.isEmpty
            ) { usedStack, _, _, _ ->
                if (BasicAmmoHoldingItem.getAmmoCount(usedStack) > 0) 0f else 1f
            }
        }

        val possiblyEmptyAdvancedAmmoItems: List<DeferredItem<out AdvancedAmmoClipItem>> = listOf(
            ModItems.ARROW_MORTAR,
            ModItems.SEED_JAR
        )

        for (item in possiblyEmptyAdvancedAmmoItems) {
            ItemProperties.register(
                item.get(),
                ModItemModelProvider.isEmpty
            ) { usedStack, _, _, _ ->
                if (AdvancedAmmoClipItem.getAmmoCount(usedStack) > 0) 0f else 1f
            }
        }

        val possiblyEmptyAdvancedAmmoClipHoldingItems = listOf(
            ModItems.COIN_TOSSER,
            ModItems.MODIFIED_COIN_TOSSER
        )

        for (item in possiblyEmptyAdvancedAmmoClipHoldingItems) {
            ItemProperties.register(
                item.get(),
                ModItemModelProvider.isEmpty
            ) { usedStack, _, _, _ ->
                if (AmmoClipHoldingItem.getClipAmmo(usedStack) > 0) 0f else 1f
            }
        }

        ItemProperties.register(
            ModItems.LAPIS_MAGAZINE.get(),
            ModItemModelProvider.percentFull
        ) { usedStack, _, _, _ ->
            if (usedStack.item !is BasicAmmoHoldingItem) return@register 0f
            val maxAmmo = (usedStack.item as BasicAmmoHoldingItem).maxAmmo
            val count = usedStack.getOrDefault(ModDataComponents.BASIC_AMMO_COMPONENT, 0)
            return@register count.toFloat() / maxAmmo.toFloat()
        }

    }

    @SubscribeEvent
    fun registerGuiLayers(event: RegisterGuiLayersEvent) {
        event.registerBelowAll(
            OtherUtil.modResource("sniper_scope"),
            ScopeUtils::renderScope
        )
    }

}