package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.event.EventHooks
import java.util.function.Predicate

class EnderBowItem : BowItem(
    Properties()
        .durability(256)
) {

    override fun getAllSupportedProjectiles(bowStack: ItemStack): Predicate<ItemStack> {
        return Predicate { checkedStack -> checkedStack.`is`(Tags.Items.INGOTS_IRON) }
    }

    override fun onUseTick(level: Level, livingEntity: LivingEntity, stack: ItemStack, remainingUseDuration: Int) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration)

        if (level is ServerLevel) {

            val projectileStack = livingEntity.getProjectile(stack)
            if (projectileStack.isEmpty) return

            var i = this.getUseDuration(stack, livingEntity) - remainingUseDuration
            if (livingEntity is Player) {
                i = EventHooks.onArrowLoose(stack, level, livingEntity, i, !projectileStack.isEmpty)
            }
            if (i < 0) return

            val powerForTime = getPowerForTime(i)

            if (powerForTime > 0.1) {
                val list = draw(stack, projectileStack, livingEntity)

                if (list.isNotEmpty()) {
                    shoot(
                        level,
                        livingEntity,
                        livingEntity.usedItemHand,
                        stack,
                        list,
                        powerForTime * 3f,
                        1f,
                        powerForTime == 1f,
                        null
                    )
                }

            }
        }
    }

}