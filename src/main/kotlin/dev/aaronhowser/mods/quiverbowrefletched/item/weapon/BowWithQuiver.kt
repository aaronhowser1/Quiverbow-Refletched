package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.item.base.HoldsAmmo
import net.minecraft.world.item.BowItem

class BowWithQuiver(properties: Properties) : HoldsAmmo, BowItem(properties) {
    override val maxAmmo: Int = 256



}