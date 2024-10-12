package dev.aaronhowser.mods.quiverbowrefletched.registry

import com.mojang.serialization.Codec
import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.world.item.ItemStack
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

    val AMMO_CLIP_COMPONENT: DeferredHolder<DataComponentType<*>, DataComponentType<ItemStack>> =
        DATA_COMPONENT_REGISTRY.registerComponentType("ammo_clip") {
            it
                .persistent(ItemStack.CODEC)
                .networkSynchronized(ItemStack.STREAM_CODEC)
        }

}