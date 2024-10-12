package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoHoldingItem

class AdvancedAmmoClipItem(
    maxAmmo: Int,
    barColor: Int
) : AmmoHoldingItem(maxAmmo, barColor) {

    companion object {
        val SEED = AdvancedAmmoClipItem(512, 0x00FF00) // TODO: Include the thing to remember the seeds
    }

}