package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.entity.CoinTosserProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoClipHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.WeaponUtils
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class CoinTosser(
    val isModified: Boolean
) : AmmoClipHoldingItem(
    clipItem = ModItems.GOLD_MAGAZINE.get()
) {

    private val timesToShoot = if (isModified) 4 else 9

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (player.cooldowns.isOnCooldown(this) && !player.hasInfiniteMaterials()) {
            return InteractionResultHolder.fail(usedStack)
        }

        var success = false
        for (i in 0 until timesToShoot) {
            if (!tryEntityUse(player, usedStack)) {
                break
            }

            val projectile = CoinTosserProjectile(player, isModified)
            level.addFreshEntity(projectile)
            projectile.shootFromRotation(
                player,
                player.xRot,
                player.yRot,
                0.0f,
                ServerConfig.COIN_TOSSER_PROJECTILE_SPEED.get().toFloat(),
                if (isModified) 4f else 9f
            )

            success = true
        }

        if (!success) return InteractionResultHolder.fail(usedStack)

        if (!player.hasInfiniteMaterials()) {
            player.cooldowns.addCooldown(this, ServerConfig.COIN_TOSSER_COOLDOWN.get())
        }

        WeaponUtils.gunSounds(
            player,
            WeaponUtils.SoundInfo(SoundEvents.ITEM_BREAK, 1f, 3f)
        )

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

}