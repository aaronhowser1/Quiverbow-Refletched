package dev.aaronhowser.mods.quiverbowrefletched.registry

import com.mojang.serialization.Codec
import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.component.WhitelistedItemStackListComponent
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModDataComponents {

    val DATA_COMPONENT_REGISTRY: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, QuiverBowRefletched.ID)

    val AMMO_COUNT_COMPONENT: DeferredHolder<DataComponentType<*>, DataComponentType<Int>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_count") {
            it
                .persistent(Codec.INT)
                .networkSynchronized(ByteBufCodecs.VAR_INT)
        }

    val AMMO_CLIP_CONTENTS: DeferredHolder<DataComponentType<*>, DataComponentType<WhitelistedItemStackListComponent>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_clip_contents") {
            it
                .persistent(WhitelistedItemStackListComponent.CODEC)
                .networkSynchronized(WhitelistedItemStackListComponent.STREAM_CODEC)
        }

}