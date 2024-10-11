package dev.aaronhowser.mods.quiverbowrefletched.registry

import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModRegistries {

    private val registries: List<DeferredRegister<out Any>> = listOf(
        ModItems.ITEM_REGISTRY,
        ModCreativeTabs.TABS_REGISTRY,
    )

    fun register(modBus: IEventBus) {
        registries.forEach { it.register(modBus) }
    }
}