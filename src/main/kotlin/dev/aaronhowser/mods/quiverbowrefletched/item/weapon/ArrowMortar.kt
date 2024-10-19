package dev.aaronhowser.mods.quiverbowrefletched.item.weapon

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.entity.ArrowMortarProjectile
import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.AdvancedAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import kotlin.random.Random

class ArrowMortar : AdvancedAmmoClipItem(
    maxAmmo = 16,
    ammoPredicate = ItemPredicate.Builder.item().of(ModItems.ARROW_BUNDLE).build(),
    properties = getDefaultProperties(64).durability(384)
) {

    override fun use(
        level: Level,
        player: Player,
        usedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val usedStack = player.getItemInHand(usedHand)

        if (player.cooldowns.isOnCooldown(ModItems.ARROW_MORTAR.get()) && !player.hasInfiniteMaterials()) {
            return InteractionResultHolder.fail(usedStack)
        }

        if (getAmmoCount(usedStack) <= 0 && !player.hasInfiniteMaterials()) {
            return InteractionResultHolder.fail(usedStack)
        }

        val projectile: ArrowMortarProjectile

        val ammoStacks = getAmmoStacks(usedStack)
        if (ammoStacks.isEmpty()) {
            projectile = ArrowMortarProjectile(player)
        } else {
            val randomIndex = Random.nextInt(ammoStacks.size)
            val randomAmmo = ammoStacks[randomIndex].copyWithCount(1)
            projectile = ArrowMortarProjectile(player, randomAmmo)

            if (!player.hasInfiniteMaterials()) {
                ammoStacks[randomIndex].shrink(1)
            }

            setAmmo(
                usedStack,
                OtherUtil.flattenStacks(ammoStacks)
            )
        }

        level.addFreshEntity(projectile)
        projectile.shootFromRotation(
            player,
            player.xRot,
            player.yRot,
            0.0f,
            ServerConfig.ARROW_MORTAR_PROJECTILE_SPEED.get().toFloat(),
            1.0f
        )

        if (!player.hasInfiniteMaterials()) {
            player.cooldowns.addCooldown(
                ModItems.ARROW_MORTAR.get(),
                ServerConfig.ARROW_MORTAR_COOLDOWN.get()
            )
        }

        OtherUtil.recoil(
            player,
            ServerConfig.ARROW_MORTAR_RECOIL.get()
        )

        return InteractionResultHolder.sidedSuccess(usedStack, level.isClientSide)
    }

}