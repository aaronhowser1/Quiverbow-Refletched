package dev.aaronhowser.mods.quiverbowrefletched.item.component

import com.mojang.serialization.Codec
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack

data class SingleStackComponent(
    val stack: ItemStack,
) {

    companion object {
        val EMPTY = SingleStackComponent(ItemStack.EMPTY)

        val CODEC: Codec<SingleStackComponent> =
            ItemStack.CODEC.xmap(::SingleStackComponent, SingleStackComponent::stack)

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SingleStackComponent> =
            ItemStack.STREAM_CODEC.map(::SingleStackComponent, SingleStackComponent::stack)
    }

}