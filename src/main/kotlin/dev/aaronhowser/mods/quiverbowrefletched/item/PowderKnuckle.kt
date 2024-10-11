package dev.aaronhowser.mods.quiverbowrefletched.item

import dev.aaronhowser.mods.quiverbowrefletched.item.base.WeaponBase
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class PowderKnuckle(
    val isModified: Boolean
) : WeaponBase(
    maxAmmo = 8
) {

    override fun onLeftClickEntity(stack: ItemStack, player: Player, entity: Entity): Boolean {
        if (player.level().isClientSide) return false
        if (entity !is LivingEntity) return false

        if (!consumeAmmo(stack, 1)) return false

        entity.remainingFireTicks = 2 * 20
        entity.level().explode(
            player,
            entity.x,
            entity.y,
            entity.z,
            1.5f,
            Level.ExplosionInteraction.TNT
        )

        return true
    }

}