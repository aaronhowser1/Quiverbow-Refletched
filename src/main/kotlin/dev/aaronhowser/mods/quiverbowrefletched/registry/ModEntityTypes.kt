package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderBowGuideProjectile
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModEntityTypes {

    val ENTITY_TYPE_REGISTRY: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, QuiverBowRefletched.ID)

    val ENDER_BOW_GUIDE_PROJECTILE: DeferredHolder<EntityType<*>, EntityType<EnderBowGuideProjectile>> =
        ENTITY_TYPE_REGISTRY.register("ender_bow_guide", Supplier {
            EntityType.Builder.of(
                ::EnderBowGuideProjectile,
                MobCategory.MISC
            )
                .sized(0.5f, 0.5f)
                .build("ender_bow_guide")
        })

}