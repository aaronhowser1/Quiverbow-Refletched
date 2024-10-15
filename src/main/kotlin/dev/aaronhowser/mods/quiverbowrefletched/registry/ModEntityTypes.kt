package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.entity.AquaAcceleratorProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.ArrowMortarProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderBowGuideProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.SilkenSpinnerProjectile
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

    val AQUA_ACCELERATOR_PROJECTILE: DeferredHolder<EntityType<*>, EntityType<AquaAcceleratorProjectile>> =
        ENTITY_TYPE_REGISTRY.register("aqua_accelerator_shot", Supplier {
            EntityType.Builder.of(
                ::AquaAcceleratorProjectile,
                MobCategory.MISC
            )
                .sized(0.5f, 0.5f)
                .build("aqua_accelerator_shot")
        })

    val SILKEN_SPINNER_PROJECTILE: DeferredHolder<EntityType<*>, EntityType<SilkenSpinnerProjectile>> =
        ENTITY_TYPE_REGISTRY.register("silken_spinner_shot", Supplier {
            EntityType.Builder.of(
                ::SilkenSpinnerProjectile,
                MobCategory.MISC
            )
                .sized(0.5f, 0.5f)
                .build("silken_spinner_shot")
        })

    val ARROW_MORTAR_PROJECTILE: DeferredHolder<EntityType<*>, EntityType<ArrowMortarProjectile>> =
        ENTITY_TYPE_REGISTRY.register("arrow_mortar_shot", Supplier {
            EntityType.Builder.of(
                ::ArrowMortarProjectile,
                MobCategory.MISC
            )
                .sized(0.5f, 0.5f)
                .build("arrow_mortar_shot")
        })

}