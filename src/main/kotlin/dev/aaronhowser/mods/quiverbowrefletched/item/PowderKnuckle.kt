package dev.aaronhowser.mods.quiverbowrefletched.item

import dev.aaronhowser.mods.quiverbowrefletched.item.base.WeaponBase
import dev.aaronhowser.mods.quiverbowrefletched.util.ClientUtil
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class PowderKnuckle(
    val isModified: Boolean
) : WeaponBase(
    maxAmmo = 8
) {

    override fun onLeftClickEntity(stack: ItemStack, player: Player, entity: Entity): Boolean {
        if (player.level().isClientSide) return false
        if (entity !is LivingEntity) return false

        val canShoot = player.isCreative || consumeAmmo(stack, 1)
        if (!canShoot) return false

        entity.remainingFireTicks = 2 * 20
        entity.level().explode(
            player,
            entity.x,
            entity.y,
            entity.z,
            1.5f,
            Level.ExplosionInteraction.MOB
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

        val amount = if (ClientUtil.playerIsCreative()) "∞" else getAmmo(stack).toString()

        tooltipComponents.add(Component.literal("Gunpowder: $amount/$maxAmmo"))
        tooltipComponents.add(Component.literal("Explosion with radius 1.5 on hit"))
        tooltipComponents.add(Component.literal("Punch mobs or right-click mobs"))
        tooltipComponents.add(Component.literal("Craft with up to 8 Gunpowder to reload"))
        tooltipComponents.add(Component.literal("Not safe to use"))
    }

}