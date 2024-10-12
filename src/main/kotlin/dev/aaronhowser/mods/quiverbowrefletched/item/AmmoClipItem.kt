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
    val ammoTag: TagKey<Item>,
    barColor: Int
) : AmmoHoldingItem(maxAmmo, barColor) {

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (!player.isSecondaryUseActive) return InteractionResultHolder.pass(usedStack)

        if (!level.isClientSide) {
            for (iteratedStack in player.inventory.items) {
                if (!iteratedStack.`is`(ammoTag)) continue

                while (iteratedStack.count > 0 && getAmmo(usedStack) < maxAmmo) {
                    iteratedStack.shrink(1)
                    modifyAmmoCount(usedStack, +1)
                }

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
        val SUGAR = AmmoClipItem(200, ModItemTagsProvider.AMMO_SUGAR_ENGINE, 0x00FF00)
        val SEED = AmmoClipItem(512, ModItemTagsProvider.AMMO_SEED_JAR, 0x00FF00) // TODO: This should be its own thing that remembers the seeds
        val OBSIDIAN = AmmoClipItem(16, ModItemTagsProvider.AMMO_OBSIDIAN, 0x666666)
        val GOLD = AmmoClipItem(72, ModItemTagsProvider.AMMO_GOLD_NUGGET, 0xFFD700)
        val THORN = AmmoClipItem(64, ModItemTagsProvider.AMMO_THORN, 0x00FF00)
        val LAPIS = AmmoClipItem(150, ModItemTagsProvider.AMMO_LAPIS, 0x0000FF)
        val REDSTONE = AmmoClipItem(64, ModItemTagsProvider.AMMO_REDSTONE, 0xFF0000)
        val LARGE_NETHERRACK = AmmoClipItem(200, ModItemTagsProvider.AMMO_LARGE_NETHERRACK, 0x800000)
        val LARGE_REDSTONE = AmmoClipItem(200, ModItemTagsProvider.AMMO_LARGE_REDSTONE, 0xFF0000)
        val ENDER_QUARTZ = AmmoClipItem(8, ModItemTagsProvider.AMMO_QUARTZ, 0xFFFFFF)
    }

}