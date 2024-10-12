package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModDataComponents {

    val DATA_COMPONENT_REGISTRY: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, QuiverBowRefletched.ID)

    val AMMO_CLIP_CONTENTS: DeferredHolder<DataComponentType<*>, DataComponentType<ItemStackListComponent>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_clip_contents") {
            it
                .persistent(ItemStackListComponent.CODEC)
                .networkSynchronized(ItemStackListComponent.STREAM_CODEC)
        }

}