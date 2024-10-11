package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.component.AmmoCountItemComponent
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModDataComponents {

    val DATA_COMPONENT_REGISTRY: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, QuiverBowRefletched.ID)

    val AMMO_COUNT_COMPONENT: DeferredHolder<DataComponentType<*>, DataComponentType<AmmoCountItemComponent>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_count") {
            it
                .persistent(AmmoCountItemComponent.CODEC)
                .networkSynchronized(AmmoCountItemComponent.STREAM_CODEC)
        }

}