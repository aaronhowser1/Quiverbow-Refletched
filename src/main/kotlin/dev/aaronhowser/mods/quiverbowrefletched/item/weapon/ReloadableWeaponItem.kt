package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.entity.EnderRifleProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.FenFireProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.FrostLancerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.entity.SilkenSpinnerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
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

abstract class ReloadableWeaponItem(
    maxAmmo: Int,
    barColor: Int
) : BasicAmmoHoldingItem(
    maxAmmo = maxAmmo,
    barColor = barColor
) {

    protected abstract fun getProjectile(player: Player): Projectile

    protected abstract val projectileSpeed: Float
    protected abstract val cooldown: Int
    protected abstract val reloadItems: Map<Item, Int>

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (player.cooldowns.isOnCooldown(this) && !player.hasInfiniteMaterials()) {
            return InteractionResultHolder.fail(usedStack)
        }

        if (!entityUse(player, usedStack)) {
            return InteractionResultHolder.fail(usedStack)
        }

        val projectile = getProjectile(player)
        level.addFreshEntity(projectile)
        projectile.shootFromRotation(
            player,
            player.xRot,
            player.yRot,
            0.0f,
            projectileSpeed,
            1.0f
        )

        if (!player.hasInfiniteMaterials()) {
            player.cooldowns.addCooldown(this, cooldown)
        }

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
        val SILKEN_SPINNER = object : ReloadableWeaponItem(
            maxAmmo = 8,
            barColor = 0x999999
        ) {
            override fun getProjectile(player: Player): Projectile = SilkenSpinnerProjectile(player)

            override val projectileSpeed: Float
                get() = ServerConfig.SILKEN_SPINNER_PROJECTILE_SPEED.get().toFloat()

            override val cooldown: Int
                get() = ServerConfig.SILKEN_SPINNER_COOLDOWN.get()

            override val reloadItems: Map<Item, Int> = mapOf(Items.COBWEB to 1)
        }

        val FEN_FIRE = object : ReloadableWeaponItem(
            maxAmmo = 32,
            barColor = 0xFFA500
        ) {
            override fun getProjectile(player: Player): Projectile = FenFireProjectile(player)

            override val projectileSpeed: Float
                get() = ServerConfig.FEN_FIRE_PROJECTILE_SPEED.get().toFloat()

            override val cooldown: Int
                get() = ServerConfig.FEN_FIRE_COOLDOWN.get()

            override val reloadItems: Map<Item, Int> = mapOf(
                Items.GLOWSTONE to 4,
                Items.GLOWSTONE_DUST to 1
            )
        }

        val ENDER_RIFLE = object : ReloadableWeaponItem(
            maxAmmo = 16,
            barColor = 0x5A2991
        ) {
            override fun getProjectile(player: Player): Projectile = EnderRifleProjectile(player)

            override val projectileSpeed: Float
                get() = ServerConfig.ENDER_RIFLE_PROJECTILE_SPEED.get().toFloat()

            override val cooldown: Int
                get() = ServerConfig.ENDER_RIFLE_COOLDOWN.get()

            override val reloadItems: Map<Item, Int> = mapOf(Items.IRON_INGOT to 1)
        }

        val FROST_LANCER = object : ReloadableWeaponItem(
            maxAmmo = 4,
            barColor = 0x00FFFF
        ) {
            override fun getProjectile(player: Player): Projectile = FrostLancerProjectile(player)

            override val projectileSpeed: Float
                get() = ServerConfig.FROST_LANCER_PROJECTILE_SPEED.get().toFloat()

            override val cooldown: Int
                get() = ServerConfig.FROST_LANCER_COOLDOWN.get()

            override val reloadItems: Map<Item, Int> = mapOf(ModItems.COLD_IRON_CLIP.get() to 4)
        }
    }

}