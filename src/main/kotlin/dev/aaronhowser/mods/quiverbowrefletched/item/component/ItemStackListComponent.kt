package dev.aaronhowser.mods.quiverbowrefletched.item.component

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack

data class ItemStackListComponent(
    val maxItems: Int,
    val items: List<ItemStack>
) {

    constructor(maxItems: Int) : this(maxItems, emptyList())
    constructor(itemStack: ItemStack) : this(1, listOf(itemStack))

    companion object {
        val CODEC: Codec<ItemStackListComponent> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.INT
                        .fieldOf("maxItems")
                        .forGetter(ItemStackListComponent::maxItems),
                    ItemStack.CODEC.listOf()
                        .fieldOf("items")
                        .forGetter(ItemStackListComponent::items)
                ).apply(instance, ::ItemStackListComponent)
            }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ItemStackListComponent> =
            StreamCodec.composite(
                ByteBufCodecs.INT, ItemStackListComponent::maxItems,
                ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), ItemStackListComponent::items,
                ::ItemStackListComponent
            )

    }

}