package dev.aaronhowser.mods.quiverbowrefletched.item.base

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.UseAnim
import net.minecraft.world.level.Level
import net.neoforged.neoforge.event.EventHooks

abstract class BowWithAmmoItem(maxAmmo: Int) : WeaponBase(maxAmmo) {

    companion object {
        fun getPowerForTime(charge: Int): Float = BowItem.getPowerForTime(charge)
    }

    override fun getUseDuration(stack: ItemStack, entity: LivingEntity): Int = 72000
    override fun getUseAnimation(stack: ItemStack): UseAnim = UseAnim.BOW

    override fun use(
        level: Level,
        player: Player,
        hand: InteractionHand
    ): InteractionResultHolder<ItemStack> {

        val itemstack = player.getItemInHand(hand)
        val flag = !player.getProjectile(itemstack).isEmpty
        val ret = EventHooks.onArrowNock(itemstack, level, player, hand, flag)

        if (ret != null) {
            return ret
        } else if (!player.hasInfiniteMaterials() && !flag) {
            return InteractionResultHolder.fail(itemstack)
        } else {
            player.startUsingItem(hand)
            return InteractionResultHolder.consume(itemstack)
        }
    }

}