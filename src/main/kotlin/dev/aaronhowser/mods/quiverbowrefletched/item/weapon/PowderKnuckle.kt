package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.BlockTags
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.CommonHooks
import net.neoforged.neoforge.common.Tags

class PowderKnuckle(
    val isModified: Boolean
) : BasicAmmoHoldingItem(
    maxAmmo = 8
) {

    private val explosionRadius: Float
        get() = if (!isModified) {
            ServerConfig.POWDER_KNUCKLE_EXPLOSION_RADIUS.get()
        } else {
            ServerConfig.MODIFIED_POWDER_KNUCKLE_EXPLOSION_RADIUS.get()
        }.toFloat()

    override fun onLeftClickEntity(stack: ItemStack, player: Player, entity: Entity): Boolean {
        if (player.level().isClientSide) return false
        if (entity !is LivingEntity) return false

        val canShoot = tryEntityUse(player, stack)
        if (!canShoot) return false

        val shouldDamageBlocks = if (!isModified) {
            ServerConfig.POWDER_KNUCKLE_GRIEFING.get()
        } else {
            ServerConfig.MODIFIED_POWDER_KNUCKLE_GRIEFING.get()
        }

        entity.remainingFireTicks = 2 * 20
        entity.level().explode(
            player,
            entity.x,
            entity.y,
            entity.z,
            explosionRadius,
            if (shouldDamageBlocks) Level.ExplosionInteraction.TNT else Level.ExplosionInteraction.NONE
        )

        return true
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        val player = context.player ?: return InteractionResult.FAIL

        val canUse = tryEntityUse(player, context.itemInHand)
        if (!canUse) return InteractionResult.FAIL

        val level = context.level
        if (level.isClientSide) return InteractionResult.SUCCESS

        val clickedPos = context.clickedPos
        val explosion = level.explode(
            player,
            clickedPos.x + 0.5,
            clickedPos.y + 0.5,
            clickedPos.z + 0.5,
            explosionRadius,
            if (isModified) Level.ExplosionInteraction.NONE else Level.ExplosionInteraction.TNT
        )

        if (isModified) {
            val clickedFace = context.clickedFace
            val centerOfVolumeToMine = clickedPos.relative(clickedFace, -1)

            val tool = context.itemInHand.copy()
            tool.enchant(level.holderOrThrow(Enchantments.SILK_TOUCH), 1)

            for (dX in -1..1) for (dY in -1..1) for (dz in -1..1) {
                val blockPos = centerOfVolumeToMine.offset(dX, dY, dz)
                mineBlock(level, blockPos, player, explosion, tool)
            }
        }

        return InteractionResult.CONSUME
    }

    private fun mineBlock(
        level: Level,
        blockPos: BlockPos,
        player: Player,
        explosion: Explosion,
        tool: ItemStack
    ) {
        if (!level.mayInteract(player, blockPos)) return

        val blockState = level.getBlockState(blockPos)
        if (blockState.isEmpty || !blockState.fluidState.isEmpty) return
        if (level.getBlockEntity(blockPos) != null) return
        if (blockState.requiresCorrectToolForDrops() && blockState.`is`(BlockTags.NEEDS_DIAMOND_TOOL)) return

        val explosionResistance = blockState.block.getExplosionResistance(
            blockState,
            level,
            blockPos,
            explosion
        )
        if (explosionResistance > 1000) return

        if (!CommonHooks.canEntityDestroy(level, blockPos, player)) return

        Block.dropResources(
            blockState,
            level,
            blockPos,
            null,
            player,
            tool
        )

        level.destroyBlock(blockPos, false, player)
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
        if (!otherStack.`is`(Tags.Items.GUNPOWDERS)) return false

        val amount = otherStack.count
        val amountToInsert = minOf(maxAmmo - getAmmoCount(thisStack), amount)

        modifyAmmoCount(thisStack, amountToInsert)
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

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)

        tooltipComponents.add(Component.literal("Explosion with radius $explosionRadius on hit").withStyle(ChatFormatting.GREEN))

        if (isModified) {
            tooltipComponents.add(Component.literal("Right-click block for 3x3x3 silk touch mining").withStyle(ChatFormatting.GREEN))
        } else {
            tooltipComponents.add(Component.literal("Punch mobs or right-click blocks").withStyle(ChatFormatting.GREEN))
        }

        tooltipComponents.add(Component.literal("Not safe to use"))
    }

}