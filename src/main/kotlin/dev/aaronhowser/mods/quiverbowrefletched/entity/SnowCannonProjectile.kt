package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.datagen.tag.ModEntityTypeTagsProvider
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import net.minecraft.core.Direction
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SnowLayerBlock
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import kotlin.random.Random

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

        var damage = Random.nextDouble(
            ServerConfig.SNOW_CANNON_DAMAGE_MINIMUM.get(),
            ServerConfig.SNOW_CANNON_DAMAGE_MAXIMUM.get()
        ).toFloat()

        if (entityHit.type.`is`(ModEntityTypeTagsProvider.WEAK_TO_SNOW_CANNON)) {
            damage *= 3f
        }

        val damageSource = this.damageSources().thrown(this, owner)
        entityHit.hurt(damageSource, damage)

        entityHit.addEffect(
            MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                ServerConfig.SNOW_CANNON_SLOWNESS_DURATION.get(),
                ServerConfig.SNOW_CANNON_SLOWNESS_STRENGTH.get()
            )
        )

        this.discard()
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)
        this.discard()

        val posHit = result.blockPos
        val sideHit = result.direction

        val posNext = posHit.relative(sideHit)
        val blockNext = level().getBlockState(posNext)

        val posBelowNext = posNext.relative(Direction.DOWN)
        val blockBelowNext = level().getBlockState(posBelowNext)

        if (blockNext.canBeReplaced() && blockBelowNext.isFaceSturdy(level(), posBelowNext, Direction.UP)) {

            val layers = if (blockNext.block == Blocks.SNOW && blockNext.hasProperty(SnowLayerBlock.LAYERS)) {
                minOf(
                    blockNext.getValue(SnowLayerBlock.LAYERS) + 1,
                    SnowLayerBlock.MAX_HEIGHT
                )
            } else {
                1
            }

            level().setBlockAndUpdate(
                posNext,
                Blocks.SNOW.defaultBlockState()
                    .setValue(SnowLayerBlock.LAYERS, layers)
            )

        }

    }

    override fun getDefaultItem(): Item {
        return Items.SNOWBALL
    }
}