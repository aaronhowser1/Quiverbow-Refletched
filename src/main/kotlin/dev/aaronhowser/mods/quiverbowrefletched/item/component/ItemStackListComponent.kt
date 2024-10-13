package dev.aaronhowser.mods.quiverbowrefletched.item.component

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack

data class ItemStackListComponent(
    val maxAmount: Int,
    val stacks: List<ItemStack>
) {

    constructor(maxItems: Int) : this(maxItems, emptyList())
    constructor(itemStack: ItemStack) : this(1, listOf(itemStack))

    fun getTotalAmount(): Int = stacks.sumOf { it.count }

    companion object {
        val CODEC: Codec<ItemStackListComponent> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.INT
                        .fieldOf("max_amount")
                        .forGetter(ItemStackListComponent::maxAmount),
                    ItemStack.CODEC.listOf()
                        .fieldOf("stacks")
                        .forGetter(ItemStackListComponent::stacks)
                ).apply(instance, ::ItemStackListComponent)
            }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ItemStackListComponent> =
            StreamCodec.composite(
                ByteBufCodecs.INT, ItemStackListComponent::maxAmount,
                ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), ItemStackListComponent::stacks,
                ::ItemStackListComponent
            )

    }

}