package dev.aaronhowser.mods.quiverbowrefletched.item

import dev.aaronhowser.mods.quiverbowrefletched.datagen.tag.ModItemTagsProvider
import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoHoldingItem
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.TagKey
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class AmmoClipItem(
    maxAmmo: Int,
    val ammoTag: TagKey<Item>
) : AmmoHoldingItem(maxAmmo = maxAmmo) {

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (!player.isSecondaryUseActive) return InteractionResultHolder.pass(usedStack)

        if (!level.isClientSide) {
            for (stack in player.inventory.items) {
                if (stack.`is`(ammoTag)) continue

                val currentAmmo = getAmmo(usedStack)
                if (currentAmmo >= maxAmmo) break

                val stackSize = stack.count

                val remainingAmmo = maxAmmo - currentAmmo
                val amountToInsert = minOf(remainingAmmo, stackSize)

                stack.shrink(amountToInsert)
                modifyAmmoCount(usedStack, amountToInsert)

                level.playSound(
                    null,
                    player,
                    SoundEvents.ITEM_PICKUP,
                    SoundSource.PLAYERS,
                    1f,
                    0.33f
                )
            }
        }

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val amount = getAmmo(stack)
        tooltipComponents.add(Component.literal("Ammo: $amount/$maxAmmo"))
    }


    companion object {
        val SUGAR = AmmoClipItem(64, ModItemTagsProvider.AMMO_SUGAR_ENGINE)
        val SEED = AmmoClipItem(512, ModItemTagsProvider.AMMO_SEED_JAR) // TODO: This should be its own thing that remembers the seeds
        val OBSIDIAN = AmmoClipItem(16, ModItemTagsProvider.AMMO_OBSIDIAN)
        val GOLD = AmmoClipItem(72, ModItemTagsProvider.AMMO_GOLD_NUGGET)
        val THORN = AmmoClipItem(64, ModItemTagsProvider.AMMO_THORN)
        val LAPIS = AmmoClipItem(150, ModItemTagsProvider.AMMO_LAPIS)
        val REDSTONE = AmmoClipItem(64, ModItemTagsProvider.AMMO_REDSTONE)
        val LARGE_NETHERRACK = AmmoClipItem(200, ModItemTagsProvider.AMMO_LARGE_NETHERRACK)
        val LARGE_REDSTONE = AmmoClipItem(200, ModItemTagsProvider.AMMO_LARGE_REDSTONE)
        val ENDER_QUARTZ = AmmoClipItem(8, ModItemTagsProvider.AMMO_QUARTZ)
    }

}