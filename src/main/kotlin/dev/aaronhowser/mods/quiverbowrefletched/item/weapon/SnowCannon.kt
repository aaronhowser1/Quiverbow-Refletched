package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.entity.SnowCannonProjectile
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level

class SnowCannon : ReloadableWeaponItem(
    ::SnowCannonProjectile,
    mapOf(
        Items.SNOWBALL to 1,
        Items.SNOW_BLOCK to 4
    ),
    maxAmmo = 32,
    barColor = 0xFFFFFF
) {

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (player.cooldowns.isOnCooldown(ModItems.SNOW_CANNON.get())) {
            return InteractionResultHolder.fail(usedStack)
        }

        repeat(4) {
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
                ServerConfig.SNOW_CANNON_PROJECTILE_SPEED.get().toFloat(),
                6.0f
            )
        }

        player.cooldowns.addCooldown(
            ModItems.SNOW_CANNON.get(),
            ServerConfig.SNOW_CANNON_COOLDOWN.get()
        )

        OtherUtil.recoil(player, ServerConfig.SNOW_CANNON_RECOIL.get().toFloat())

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

}