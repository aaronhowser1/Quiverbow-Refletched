package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.WeaponUtils
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.BlockTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.phys.HitResult
import net.neoforged.neoforge.common.CommonHooks
import net.neoforged.neoforge.common.Tags

class FlintDuster : BasicAmmoHoldingItem(
    maxAmmo = 256,
    barColor = 0x555555
) {

    companion object {
        private const val AMMO_PER_FLINT_BOX = 32
    }

    override fun overrideOtherStackedOnMe(
        thisStack: ItemStack,
        otherStack: ItemStack,
        slot: Slot,
        action: ClickAction,
        player: Player,
        access: SlotAccess
    ): Boolean {
        if (thisStack.count != 1) return false
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false
        if (getAmmoCount(thisStack) >= maxAmmo) return false
        if (otherStack.item != ModItems.BOX_FLINT_DUST.get()) return false

        //TODO: Make sure I can insert if I have <32 missing ammo

        val otherStackSize = otherStack.count
        val amountNeededForFullAmmo = (maxAmmo - getAmmoCount(thisStack)) / AMMO_PER_FLINT_BOX
        val amountToInsert = minOf(amountNeededForFullAmmo, otherStackSize)

        if (amountToInsert <= 0) return false

        modifyAmmoCount(
            thisStack,
            (amountToInsert * AMMO_PER_FLINT_BOX).coerceAtMost(maxAmmo)
        )
        otherStack.shrink(amountToInsert)

        player.level().playSound(
            null,
            player.blockPosition(),
            SoundEvents.ITEM_PICKUP,
            SoundSource.PLAYERS,
            1f,
            0.33f
        )

        return true
    }

    //TODO: Implement targeting entities
    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        val blockLookedAt = level.clip(
            ClipContext(
                player.eyePosition,
                player.eyePosition.add(
                    player
                        .calculateViewVector(player.xRot, player.yRot)
                        .normalize()
                        .scale(ServerConfig.FLINT_DUSTER_RANGE.get())
                ),
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
            )
        )

        if (blockLookedAt.type == HitResult.Type.MISS) return InteractionResultHolder.fail(usedStack)

        return if (mineBlock(
                level,
                blockLookedAt.blockPos,
                player,
                usedStack
            )
        ) {
            WeaponUtils.gunSounds(
                player,
                WeaponUtils.SoundInfo(SoundEvents.BAT_TAKEOFF, 0.5f, 0.6f)
            )

            InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
        } else {
            InteractionResultHolder.fail(usedStack)
        }
    }

    //TODO: Beam projectile
    private fun mineBlock(
        level: Level,
        blockPos: BlockPos,
        player: Player,
        tool: ItemStack
    ): Boolean {
        if (!level.mayInteract(player, blockPos)) return false

        val blockState = level.getBlockState(blockPos)
        if (blockState.`is`(BlockTags.NEEDS_DIAMOND_TOOL) || blockState.`is`(Tags.Blocks.ORES)) return false
        if (blockState.block.defaultDestroyTime() > 40) return false
        if (level.getBlockEntity(blockPos) != null) return false

        if (!CommonHooks.canEntityDestroy(level, blockPos, player)) return false

        if (!tryEntityUse(player, tool)) return false

        Block.dropResources(
            blockState,
            level,
            blockPos,
            null,
            player,
            tool
        )

        level.destroyBlock(blockPos, false, player)

        return true
    }

}