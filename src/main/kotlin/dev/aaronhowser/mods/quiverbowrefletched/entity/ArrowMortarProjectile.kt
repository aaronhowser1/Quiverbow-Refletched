package dev.aaronhowser.mods.quiverbowrefletched.entity

import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.AdvancedAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModEntityTypes
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.core.Direction
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ProjectileItem
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.Vec3
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.toVec3

class ArrowMortarProjectile(
    entityType: EntityType<ArrowMortarProjectile>,
    level: Level
) : AbstractArrow(entityType, level) {

    private var arrowBundle: ItemStack = ModItems.ARROW_BUNDLE.toStack()

    constructor(shooter: LivingEntity) : this(
        ModEntityTypes.ARROW_MORTAR_PROJECTILE.get(),
        shooter.level()
    ) {
        this.moveTo(shooter.x, shooter.eyeY, shooter.z)
        this.owner = shooter
    }

    constructor(shooter: LivingEntity, arrowBundle: ItemStack) : this(shooter) {
        this.arrowBundle = arrowBundle
    }

    override fun onHitEntity(result: EntityHitResult) {
        super.onHitEntity(result)

        val entity = result.entity
        entity.invulnerableTime = 0

        shootBurst(this.deltaMovement.scale(-1.0))
        this.discard()
    }

    private fun shootBurst(angle: Vec3) {
        val arrowStacks = AdvancedAmmoClipItem.getAmmoStacks(arrowBundle)
            .flatMap { ammoStack -> List(ammoStack.count) { ammoStack.copyWithCount(1) } }

        for (arrowStack in arrowStacks) {

            val projectile = (arrowStack.item as? ProjectileItem)?.asProjectile(
                this.level(),
                this.position(),
                arrowStack,
                Direction.UP
            ) ?: continue

            projectile.moveTo(this.position())

            projectile.shoot(
                angle.x,
                angle.y,
                angle.z,
                1f,
                75f
            )

            level().addFreshEntity(projectile)
        }
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)

        val sideHit = result.direction

        shootBurst(sideHit.normal.toVec3())
        this.discard()
    }

    override fun getDefaultPickupItem(): ItemStack {
        return ItemStack.EMPTY
    }

}