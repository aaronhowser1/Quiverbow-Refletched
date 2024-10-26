package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil.isTrue
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import kotlin.random.Random

class CoinTosserProjectile(
    entityType: EntityType<CoinTosserProjectile>,
    level: Level
) : ThrowableItemProjectile(entityType, level) {

    private var isModified: Boolean = false

    constructor(
        shooter: LivingEntity,
        isModified: Boolean
    ) : this(
        ModEntityTypes.COIN_TOSSER_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
        this.isModified = isModified
    }

    override fun getDefaultItem(): Item {
        return Items.GOLD_NUGGET
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)

        val hasCreativeOwner = (this.owner as? LivingEntity)?.hasInfiniteMaterials().isTrue
        val configSaysShouldDropNugget = if (isModified) {
            ServerConfig.MODIFIED_COIN_TOSSER_DROP_NUGGET_ON_MISS.get()
        } else {
            ServerConfig.COIN_TOSSER_DROP_NUGGET_ON_MISS.get()
        }

        if (configSaysShouldDropNugget && !hasCreativeOwner) {
            this.spawnAtLocation(Items.GOLD_NUGGET)
        }

        this.discard()
    }

    override fun onHitEntity(result: EntityHitResult) {
        val hitEntity = result.entity as? LivingEntity ?: return
        val damageSource = this.damageSources().thrown(this, owner)

        val damage = Random.nextDouble(
            from = if (isModified) ServerConfig.MODIFIED_COIN_TOSSER_DAMAGE_MINIMUM.get() else ServerConfig.COIN_TOSSER_DAMAGE_MINIMUM.get(),
            until = if (isModified) ServerConfig.MODIFIED_COIN_TOSSER_DAMAGE_MAXIMUM.get() else ServerConfig.COIN_TOSSER_DAMAGE_MAXIMUM.get()
        )

        hitEntity.hurt(damageSource, damage.toFloat())
        hitEntity.hurtTime = 0

        this.discard()
    }

}