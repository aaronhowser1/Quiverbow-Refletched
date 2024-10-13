package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.AdvancedAmmoClipItem
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.UseAnim
import net.minecraft.world.level.Level
import net.neoforged.neoforge.event.EventHooks

class BowWithQuiverItem : AdvancedAmmoClipItem(
    maxAmmo = 64,
    barColor = 0x00FF00,
    allowedAmmoTag = ItemTags.ARROWS,
    properties = getDefaultProperties(64).durability(384)
) {

    override fun getUseAnimation(stack: ItemStack): UseAnim = UseAnim.BOW
    override fun getUseDuration(stack: ItemStack, entity: LivingEntity): Int = 72000

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        val canUseBow = getAmmoCount(usedStack) > 0 || player.hasInfiniteMaterials()
        if (!canUseBow) return InteractionResultHolder.fail(usedStack)

        val interactionResultHolder: InteractionResultHolder<ItemStack>? =
            EventHooks.onArrowNock(usedStack, level, player, player.usedItemHand, true)

        if (interactionResultHolder != null) return interactionResultHolder

        player.startUsingItem(usedHand)
        return InteractionResultHolder.consume(usedStack)
    }

    override fun releaseUsing(stack: ItemStack, level: Level, livingEntity: LivingEntity, timeLeft: Int) {
        if (livingEntity !is Player) return
        if (level !is ServerLevel) return

        val ammoStacks = getAmmoStacks(stack)

        var i = this.getUseDuration(stack, livingEntity) - timeLeft
        i = EventHooks.onArrowLoose(stack, level, livingEntity, i, true)
        if (i < 0) return

        val powerForTime = BowItem.getPowerForTime(i)
        if (powerForTime < 0.1) return

        (Items.BOW as BowItem).shoot(
            level,
            livingEntity,
            livingEntity.usedItemHand,
            stack,
            ammoStacks,
            powerForTime * 3f,
            1f,
            powerForTime == 1f,
            null
        )

        level.playSound(
            null,
            livingEntity.getX(),
            livingEntity.getY(),
            livingEntity.getZ(),
            SoundEvents.ARROW_SHOOT,
            SoundSource.PLAYERS,
            1.0f,
            1.0f / (level.getRandom().nextFloat() * 0.4f + 1.2f) + powerForTime * 0.5f
        )

        livingEntity.awardStat(Stats.ITEM_USED[this])
    }

}