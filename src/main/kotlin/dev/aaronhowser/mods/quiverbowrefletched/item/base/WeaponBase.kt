package dev.aaronhowser.mods.quiverbowrefletched.item.base

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.world.item.Item

abstract class WeaponBase(
    val maxAmmo: Int
) : Item(
    Properties()
        .stacksTo(1)
        .component(ModDataComponents.AMMO_COUNT_COMPONENT, 0)
) {

}