package dev.aaronhowser.mods.quiverbowrefletched.util

import net.minecraft.world.entity.LivingEntity

object WeaponUtils {

    fun recoil(livingEntity: LivingEntity, amount: Number) {
        val direction = livingEntity.lookAngle
        livingEntity.knockback(
            amount.toDouble() * 0.08f,
            direction.x,
            direction.z
        )
    }

}