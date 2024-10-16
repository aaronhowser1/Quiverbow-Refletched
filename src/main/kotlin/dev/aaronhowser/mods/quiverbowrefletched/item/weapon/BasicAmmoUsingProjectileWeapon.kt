package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderRifleRoundProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.FenFireProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.SilkenSpinnerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level

class BasicAmmoUsingProjectileWeapon(
    private val projectileSupplier: (Player) -> Projectile,
    private val reloadItems: Map<Item, Int>,
    maxAmmo: Int,
    barColor: Int
) : BasicAmmoHoldingItem(
    maxAmmo = maxAmmo,
    barColor = barColor
) {

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (!entityUse(player, usedStack)) {
            return InteractionResultHolder.fail(usedStack)
        }

        val projectile = projectileSupplier(player)
        level.addFreshEntity(projectile)
        projectile.shootFromRotation(
            player,
            player.xRot,
            player.yRot,
            0.0f,
            1.5f,
            1.0f
        )

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
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
        if (otherStack.item !in reloadItems) return false

        val ammoPerItem = reloadItems[otherStack.item] ?: return false
        val itemsNeededForMax = (maxAmmo - getAmmoCount(thisStack)) / ammoPerItem

        val otherStackSize = otherStack.count
        val itemsToTake = minOf(itemsNeededForMax, otherStackSize)

        if (itemsToTake <= 0) return false

        otherStack.shrink(itemsToTake)
        modifyAmmoCount(thisStack, itemsToTake * ammoPerItem)

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

    companion object {
        val SILKEN_SPINNER = BasicAmmoUsingProjectileWeapon(
            projectileSupplier = ::SilkenSpinnerProjectile,
            reloadItems = mapOf(Items.COBWEB to 1),
            maxAmmo = 8,
            barColor = 0x999999
        )

        val FEN_FIRE = BasicAmmoUsingProjectileWeapon(
            projectileSupplier = ::FenFireProjectile,
            reloadItems = mapOf(Items.GLOWSTONE to 4, Items.GLOWSTONE_DUST to 1),
            maxAmmo = 32,
            barColor = 0xFFA500
        )

        val ENDER_RIFLE = BasicAmmoUsingProjectileWeapon(
            projectileSupplier = ::EnderRifleRoundProjectile,   //TODO: increase velocity a lot
            reloadItems = mapOf(Items.IRON_INGOT to 1),
            maxAmmo = 16,
            barColor = 0x000000
        )
    }

}