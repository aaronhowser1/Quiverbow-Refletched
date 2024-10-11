package dev.aaronhowser.mods.quiverbowrefletched.item.component

import com.mojang.serialization.Codec
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

data class AmmoCountItemComponent(
    val amount: Int
) {

    companion object {
        val CODEC: Codec<AmmoCountItemComponent> =
            Codec.INT.xmap(::AmmoCountItemComponent, AmmoCountItemComponent::amount)

        val STREAM_CODEC: StreamCodec<ByteBuf, AmmoCountItemComponent> =
            ByteBufCodecs.VAR_INT.map(::AmmoCountItemComponent, AmmoCountItemComponent::amount)
    }

}