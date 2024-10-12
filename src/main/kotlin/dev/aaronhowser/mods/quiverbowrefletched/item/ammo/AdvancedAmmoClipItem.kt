package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem

class AdvancedAmmoClipItem(
    maxAmmo: Int,
    barColor: Int
) : BasicAmmoHoldingItem(maxAmmo, barColor) {

    companion object {
        val SEED = AdvancedAmmoClipItem(512, 0x00FF00) // TODO: Include the thing to remember the seeds
    }

}