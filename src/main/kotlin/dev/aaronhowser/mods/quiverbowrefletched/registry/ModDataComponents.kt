package dev.aaronhowser.mods.quiverbowrefletched.registry

import com.mojang.serialization.Codec
import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModDataComponents {

    val DATA_COMPONENT_REGISTRY: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, QuiverBowRefletched.ID)

    val BASIC_AMMO_COMPONENT: DeferredHolder<DataComponentType<*>, DataComponentType<Int>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_count") {
            it
                .persistent(Codec.INT)
                .networkSynchronized(ByteBufCodecs.VAR_INT)
        }

    val ADVANCED_AMMO_COMPONENT: DeferredHolder<DataComponentType<*>, DataComponentType<ItemStackListComponent>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_clip_contents") {
            it
                .persistent(ItemStackListComponent.CODEC)
                .networkSynchronized(ItemStackListComponent.STREAM_CODEC)
        }

}