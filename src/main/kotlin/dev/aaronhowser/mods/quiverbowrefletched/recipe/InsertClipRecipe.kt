package dev.aaronhowser.mods.quiverbowrefletched.recipe

import dev.aaronhowser.mods.quiverbowrefletched.item.AmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoClipHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.item.component.SingleStackComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModRecipeSerializers
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level

class InsertClipRecipe(
    craftingCategory: CraftingBookCategory = CraftingBookCategory.MISC
) : CustomRecipe(craftingCategory) {

    override fun matches(input: CraftingInput, level: Level): Boolean {
        var weapon: ItemStack? = null
        var clip: ItemStack? = null

        for (stack in input.items()) {
            if (stack.item is AmmoClipItem) {
                if (clip != null) return false
                clip = stack.copy()
            }

            if (stack.item is AmmoClipHoldingItem) {
                if (weapon != null) return false
                weapon = stack.copy()
            }
        }

        if (weapon == null || clip == null) return false

        return AmmoClipHoldingItem.getClip(weapon).isEmpty
    }

    override fun assemble(input: CraftingInput, registries: HolderLookup.Provider): ItemStack {
        var weapon: ItemStack? = null
        var clip: ItemStack? = null

        for (stack in input.items()) {
            if (stack.item is AmmoClipItem) {
                if (clip != null) return ItemStack.EMPTY
                clip = stack.copy()
            }

            if (stack.item is AmmoClipHoldingItem) {
                if (weapon != null) return ItemStack.EMPTY
                weapon = stack.copy()
            }
        }

        if (weapon == null || clip == null) return ItemStack.EMPTY

        weapon.set(ModDataComponents.AMMO_CLIP_COMPONENT.get(), SingleStackComponent(clip))

        return weapon
    }

    override fun canCraftInDimensions(x: Int, y: Int): Boolean {
        return x * y > 2
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return ModRecipeSerializers.INSERT_CLIP.get()
    }
}