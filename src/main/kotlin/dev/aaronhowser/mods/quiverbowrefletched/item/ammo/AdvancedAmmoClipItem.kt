package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.common.Tags

class AdvancedAmmoClipItem(
    private val maxAmmo: Int,
    private val barColor: Int,
    private val allowedAmmoTag: TagKey<Item>,
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
            ItemStackListComponent(maxAmmo)
        )
) : Item(properties) {

    companion object {
        val SEED = AdvancedAmmoClipItem(512, 0x00FF00, Tags.Items.SEEDS)
    }

    override fun isBarVisible(stack: ItemStack): Boolean {
        return true
    }

    override fun getBarColor(stack: ItemStack): Int {
        return barColor
    }

}