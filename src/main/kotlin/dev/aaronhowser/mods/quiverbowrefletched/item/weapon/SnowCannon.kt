package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.entity.SnowCannonProjectile
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
                1.5f,
                6.0f
            )
        }

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

}