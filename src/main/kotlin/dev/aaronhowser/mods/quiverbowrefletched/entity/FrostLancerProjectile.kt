package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class FrostLancerProjectile(
    entityType: EntityType<FrostLancerProjectile>,
    level: Level
) : AbstractArrow(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.FROST_LANCER_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun getDefaultGravity(): Double {
        return super.getDefaultGravity() / 2
    }

    override fun getDefaultPickupItem(): ItemStack {
        return ItemStack.EMPTY
    }
}