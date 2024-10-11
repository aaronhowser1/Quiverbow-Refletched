package dev.aaronhowser.mods.quiverbowrefletched.item.component

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

data class AmmoCountItemComponent(
    val maxAmount: Int,
    val currentAmount: Int
) {

    companion object {
        val CODEC: Codec<AmmoCountItemComponent> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.INT
                        .fieldOf("max_amount")
                        .forGetter(AmmoCountItemComponent::maxAmount),
                    Codec.INT
                        .fieldOf("current_amount")
                        .forGetter(AmmoCountItemComponent::currentAmount)
                ).apply(instance, ::AmmoCountItemComponent)
            }

        val STREAM_CODEC: StreamCodec<ByteBuf, AmmoCountItemComponent> =
            StreamCodec.composite(
                ByteBufCodecs.VAR_INT, AmmoCountItemComponent::maxAmount,
                ByteBufCodecs.VAR_INT, AmmoCountItemComponent::currentAmount,
                ::AmmoCountItemComponent
            )
    }

}