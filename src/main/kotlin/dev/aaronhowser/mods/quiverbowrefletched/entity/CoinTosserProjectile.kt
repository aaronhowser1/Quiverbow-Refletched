package dev.aaronhowser.mods.quiverbowrefletched.entity

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level

class CoinTosserProjectile(
    entityType: EntityType<CoinTosserProjectile>,
    level: Level
) : ThrowableItemProjectile(entityType, level) {

    //TODO: Possibly add Ultrakill ricochet?

    private val dropNuggetOnMiss = true     //TODO: Config

    override fun getDefaultItem(): Item {
        return Items.GOLD_NUGGET
    }

}