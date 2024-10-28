package dev.aaronhowser.mods.quiverbowrefletched.util

import net.minecraft.sounds.SoundEvent
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

    data class SoundInfo(
        val sound: SoundEvent,
        val volume: Float = 1.0f,
        val pitch: Float = 1.0f
    )

    fun gunSounds(
        entity: LivingEntity,
        fireSound: SoundInfo,
    ) {
        gunSounds(entity, fireSound, null, 0)
    }

    fun gunSounds(
        entity: LivingEntity,
        fireSound: SoundInfo,
        readySound: SoundInfo?,
        readyTime: Int
    ) {
        entity.playSound(fireSound.sound, fireSound.volume, fireSound.pitch)

        if (readySound != null) {
            ServerScheduler.scheduleTaskInTicks(readyTime) {
                entity.playSound(readySound.sound, readySound.volume, readySound.pitch)
            }
        }
    }

}