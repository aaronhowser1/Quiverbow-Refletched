package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.entity.SilkenSpinnerProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.base.BasicAmmoHoldingItem
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level

class BasicAmmoUsingProjectileWeapon private constructor(
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



    companion object {
        val SILKEN_SPINNER = BasicAmmoUsingProjectileWeapon(
            projectileSupplier = { player -> SilkenSpinnerProjectile(player) },
            reloadItems = mapOf(Items.COBWEB to 1),
            maxAmmo = 8,
            barColor = 0x999999
        )

        val FEN_FIRE = BasicAmmoUsingProjectileWeapon(
            projectileSupplier = { player -> SilkenSpinnerProjectile(player) },
            reloadItems = mapOf(Items.GLOWSTONE to 4, Items.GLOWSTONE_DUST to 1),
            maxAmmo = 32,
            barColor = 0xFFA500
        )

    }

}