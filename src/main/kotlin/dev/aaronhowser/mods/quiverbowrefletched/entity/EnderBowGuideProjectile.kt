package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.Arrow
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class EnderBowGuideProjectile(
    entityType: EntityType<EnderBowGuideProjectile>,
    level: Level
) : Arrow(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.ENDER_BOW_GUIDE_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.discard()
    }

    override fun onHitEntity(result: EntityHitResult) {
        super.discard()
    }

}