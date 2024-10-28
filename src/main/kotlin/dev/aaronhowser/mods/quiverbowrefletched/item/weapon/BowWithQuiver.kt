package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.AdvancedAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
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
import kotlin.random.Random

class BowWithQuiver : AdvancedAmmoClipItem(
    maxAmmo = 64,
    allowedAmmoTag = ItemTags.ARROWS,
    properties = getDefaultProperties(64).durability(384)
) {

    override fun getUseAnimation(stack: ItemStack): UseAnim = UseAnim.BOW
    override fun getUseDuration(stack: ItemStack, entity: LivingEntity): Int = 72000

    override fun insertAmmo(
        thisStack: ItemStack,
        otherStack: ItemStack,
        player: Player
    ): Boolean {
        if (!otherStack.`is`(ModItems.ARROW_BUNDLE)) return super.insertAmmo(thisStack, otherStack, player)

        val maxAmmo = getMaxAmmoAmount(thisStack)
        val currentAmmo = getAmmoCount(thisStack)
        if (currentAmmo >= maxAmmo) return false

        val bundleArrows = getAmmoStacks(otherStack)

        for (arrowStack in bundleArrows) {
            val mutableArrowStack = arrowStack.copy()
            insertAmmo(thisStack, mutableArrowStack, player)

            //TODO: Make sure this part works
            if (!mutableArrowStack.isEmpty) {
                if (!player.addItem(mutableArrowStack)) player.drop(mutableArrowStack, false)
            }
        }

        otherStack.shrink(1)
        return true
    }

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

        val ammoStack: ItemStack
        val ammoStacks = getAmmoStacks(stack)

        if (ammoStacks.isEmpty()) {
            if (livingEntity.hasInfiniteMaterials()) {
                ammoStack = Items.ARROW.defaultInstance
            } else {
                return
            }
        } else {
            val randomArrowIndex = Random.nextInt(ammoStacks.size)
            ammoStack = ammoStacks[randomArrowIndex].copyWithCount(1)

            if (!livingEntity.hasInfiniteMaterials()) {
                ammoStacks[randomArrowIndex].shrink(1)
                setAmmo(stack, OtherUtil.flattenStacks(ammoStacks))
            }
        }

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
            listOf(ammoStack),
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


    }

    //Todo: RegisterItemDecorationsEvent for ammo durability bar to be separate from the bow's actual durability

}