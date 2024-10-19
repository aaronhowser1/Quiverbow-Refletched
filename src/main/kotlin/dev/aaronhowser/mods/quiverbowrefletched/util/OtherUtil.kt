package dev.aaronhowser.mods.quiverbowrefletched.util

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

object OtherUtil {

    fun modResource(path: String): ResourceLocation =
        ResourceLocation.fromNamespaceAndPath(QuiverBowRefletched.ID, path)

    val Boolean?.isTrue: Boolean
        get() = this == true

    fun flattenStacks(input: List<ItemStack>): List<ItemStack> {
        @Suppress("NAME_SHADOWING") // Shadowing is intentional, since we don't want to touch the original stacks
        val input = input.mapNotNull { if (it.isEmpty) null else it.copy() }
        val output = mutableListOf<ItemStack>()

        for (stack in input) {
            if (stack.item !in output.map { it.item }) {
                output.add(stack)
                continue
            }

            val existingStacks = output.filter { ItemStack.isSameItemSameComponents(it, stack) }

            for (existingStack in existingStacks) {
                if (stack.isEmpty) break

                val existingStackAmount = existingStack.count
                val existingStackMaxAmount = existingStack.maxStackSize

                val amountToAdd = minOf(stack.count, existingStackMaxAmount - existingStackAmount)

                if (amountToAdd > 0) {
                    existingStack.grow(amountToAdd)
                    stack.shrink(amountToAdd)
                }
            }

            if (!stack.isEmpty) output.add(stack)
        }

        return output
    }

    fun separateStacks(input: List<ItemStack>): List<ItemStack> {
        return input.flatMap { stack -> List(stack.count) { stack.copyWithCount(1) } }
    }

    fun recoil(livingEntity: LivingEntity, amount: Number) {
        val direction = livingEntity.lookAngle
        livingEntity.knockback(
            amount.toDouble() * 0.08f,
            direction.x,
            direction.z
        )
    }

}