package dev.aaronhowser.mods.quiverbowrefletched.item.component

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack

data class WhitelistedItemStackListComponent(
    val predicate: ItemPredicate,
    val maxItems: Int,
    val items: List<ItemStack>
) {

    companion object {
        val CODEC: Codec<WhitelistedItemStackListComponent> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    ItemPredicate.CODEC
                        .fieldOf("predicate")
                        .forGetter(WhitelistedItemStackListComponent::predicate),
                    Codec.INT
                        .fieldOf("maxItems")
                        .forGetter(WhitelistedItemStackListComponent::maxItems),
                    ItemStack.CODEC.listOf()
                        .fieldOf("items")
                        .forGetter(WhitelistedItemStackListComponent::items)
                ).apply(instance, ::WhitelistedItemStackListComponent)
            }

        private val ITEM_PREDICATE_STREAM_CODEC: StreamCodec<ByteBuf, ItemPredicate> =
            ByteBufCodecs.fromCodec(ItemPredicate.CODEC)

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, WhitelistedItemStackListComponent> =
            StreamCodec.composite(
                ITEM_PREDICATE_STREAM_CODEC, WhitelistedItemStackListComponent::predicate,
                ByteBufCodecs.INT, WhitelistedItemStackListComponent::maxItems,
                ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), WhitelistedItemStackListComponent::items,
                ::WhitelistedItemStackListComponent
            )

    }

}