package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.BucketItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult

class AquaAcceleratorProjectile(
    entityType: EntityType<AquaAcceleratorProjectile>,
    level: Level
) : ThrowableItemProjectile(entityType, level) {

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.AQUA_ACCELERATOR_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    override fun onHitBlock(result: BlockHitResult) {
        val posHit = result.blockPos
        val sideHit = result.direction

        val posNext = posHit.relative(sideHit)
        val blockNext = level().getBlockState(posNext)

        if (blockNext.canBeReplaced(Fluids.WATER)) {
            (defaultItem as BucketItem).emptyContents(
                owner as? Player,
                level(),
                posNext,
                null,
                null
            )
        }

        this.discard()
    }

    override fun getDefaultItem(): Item {
        return Items.WATER_BUCKET
    }

}