package dev.aaronhowser.mods.quiverbowrefletched.registry

import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModRegistries {

    private val registries: List<DeferredRegister<out Any>> = listOf(
        ModItems.ITEM_REGISTRY,
        ModDataComponents.DATA_COMPONENT_REGISTRY,
        ModCreativeTabs.TABS_REGISTRY,
        ModEntityTypes.ENTITY_TYPE_REGISTRY,
    )

    fun register(modBus: IEventBus) {
        registries.forEach { it.register(modBus) }
    }
}