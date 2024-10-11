package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderBowGuideProjectile
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.neoforged.neoforge.common.Tags
import java.util.function.Predicate

class EnderBowItem : BowItem(
    Properties()
        .durability(256)
) {

    override fun getAllSupportedProjectiles(bowStack: ItemStack): Predicate<ItemStack> {
        return Predicate { checkedStack -> checkedStack.`is`(Tags.Items.INGOTS_IRON) }
    }

    override fun onUseTick(level: Level, livingEntity: LivingEntity, usedStack: ItemStack, remainingUseDuration: Int) {
        super.onUseTick(level, livingEntity, usedStack, remainingUseDuration)

        if (level is ClientLevel) {

            if (level.gameTime % 5 != 0L) return

            val projectileStack = livingEntity.getProjectile(usedStack)
            if (projectileStack.isEmpty) return

            val i = this.getUseDuration(usedStack, livingEntity) - remainingUseDuration
            val powerForTime = getPowerForTime(i)

            if (powerForTime > 0.1) {
                shootGuideProjectile(
                    level,
                    livingEntity,
                    powerForTime * 3f
                )
            }
        }
    }

    override fun createProjectile(level: Level, shooter: LivingEntity, weapon: ItemStack, ammo: ItemStack, isCrit: Boolean): Projectile {
        if (ammo.item == Items.STICK) {
            return EnderBowGuideProjectile(shooter)
        }

        return super.createProjectile(level, shooter, weapon, ammo, isCrit)
    }

    private fun shootGuideProjectile(
        level: ClientLevel,
        shooter: LivingEntity,
        velocity: Float
    ) {
        val inaccuracy = 1f
        val angle = 0f

        val projectile = EnderBowGuideProjectile(shooter)
        this.shootProjectile(shooter, projectile, 0, velocity, inaccuracy, angle, null)
        level.addFreshEntity(projectile)

        shooter.sendSystemMessage(Component.literal("Projectile exists: ${projectile.isAlive}"))
    }

}