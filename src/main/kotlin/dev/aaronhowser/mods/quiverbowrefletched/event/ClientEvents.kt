package dev.aaronhowser.mods.quiverbowrefletched.event

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.EnderRifle
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent

@EventBusSubscriber(
    modid = QuiverBowRefletched.ID,
    value = [Dist.CLIENT]
)
object ClientEvents {

    @SubscribeEvent
    fun onFovModifierEvent(event: ComputeFovModifierEvent) {
        EnderRifle.checkShouldZoom(event)
    }

    @SubscribeEvent
    fun onPlayerTurnEvent(event: CalculatePlayerTurnEvent) {
        EnderRifle.checkShouldLowerMouseSensitivity(event)
    }


}