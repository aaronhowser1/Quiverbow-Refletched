package dev.aaronhowser.mods.quiverbowrefletched.recipe

import dev.aaronhowser.mods.quiverbowrefletched.item.AmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModRecipeSerializers
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level

class FillClipRecipe(
    craftingCategory: CraftingBookCategory = CraftingBookCategory.MISC
) : CustomRecipe(craftingCategory) {

    override fun matches(input: CraftingInput, level: Level): Boolean {
        var clipStack: ItemStack? = null

        for (stack in input.items()) {
            if (stack.item is AmmoClipItem) {
                if (clipStack != null) return false
                clipStack = stack.copy()
            }
        }

        if (clipStack == null) return false

        val ammoPredicate = AmmoClipItem.getAmmoPredicate(clipStack)
        val ammoStacks = input.items().filter { it != clipStack && ammoPredicate.test(it) }

        return ammoStacks.isNotEmpty()
    }

    override fun assemble(input: CraftingInput, registries: HolderLookup.Provider): ItemStack {
        var clipStack: ItemStack? = null

        for (stack in input.items()) {
            if (stack.item is AmmoClipItem) {
                if (clipStack != null) return ItemStack.EMPTY
                clipStack = stack.copy()
            }
        }

        if (clipStack == null) return ItemStack.EMPTY

        val ammoPredicate = AmmoClipItem.getAmmoPredicate(clipStack)
        val ammoStacks = input.items().filter { it != clipStack && ammoPredicate.test(it) }

        val clipMaxAmmo = AmmoHoldingItem.getMaxAmmo(clipStack)

        for (ammoStack in ammoStacks) {
            while (ammoStack.count > 0 && AmmoHoldingItem.getAmmo(clipStack) < clipMaxAmmo) {
                ammoStack.shrink(1)
                AmmoHoldingItem.addAmmo(clipStack, 1)
            }
        }

        return clipStack
    }

    override fun canCraftInDimensions(x: Int, y: Int): Boolean {
        return x > 2 && y > 2
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return ModRecipeSerializers.FILL_CLIP.get()
    }

}