package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.datagen.tag.ModEntityTypeTagsProvider
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult

class SnowCannonProjectile(
    entityType: EntityType<SnowCannonProjectile>,
    level: Level
) : ThrowableItemProjectile(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.SNOW_CANNON_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun onHitEntity(result: EntityHitResult) {
        val entityHit = result.entity as? LivingEntity ?: return
        val damage = if (entityHit.type.`is`(ModEntityTypeTagsProvider.WEAK_TO_SNOW_CANNON)) 6f else 2f

        val damageSource = this.damageSources().thrown(this, owner)
        entityHit.hurt(damageSource, damage)

        this.discard()
    }

    override fun getDefaultItem(): Item {
        return Items.SNOWBALL
    }
}