package dev.aaronhowser.mods.quiverbowrefletched.item

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class PowderKnuckle(
    val isModified: Boolean
) : Item(
    Properties()
        .stacksTo(1)
) {

    override fun onLeftClickEntity(stack: ItemStack, player: Player, entity: Entity): Boolean {
        if (player.level().isClientSide) return false
        if (entity !is LivingEntity) return false



        return true
    }

}