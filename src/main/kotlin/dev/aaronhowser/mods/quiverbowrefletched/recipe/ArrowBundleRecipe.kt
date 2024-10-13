package dev.aaronhowser.mods.quiverbowrefletched.recipe

import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.ArrowBundleItem
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModRecipeSerializers
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.core.HolderLookup
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level
import net.neoforged.neoforge.common.Tags

//TODO: Emi Support
class ArrowBundleRecipe(
    craftingCategory: CraftingBookCategory = CraftingBookCategory.MISC
) : CustomRecipe(craftingCategory) {

    override fun matches(input: CraftingInput, level: Level): Boolean {
        if (input.width() != 3 || input.height() != 3) return false
        if (!input.getItem(1, 1).`is`(Tags.Items.STRINGS)) return false

        val arrows = input.items().filter { it.`is`(ItemTags.ARROWS) }
        return arrows.size == 8
    }

    override fun assemble(input: CraftingInput, registries: HolderLookup.Provider): ItemStack {
        val arrows = input.items().filter { it.`is`(ItemTags.ARROWS) }
        if (arrows.size != 8) return ItemStack.EMPTY

        return ArrowBundleItem.getStackWithArrows(OtherUtil.flattenStacks(arrows))
    }

    override fun canCraftInDimensions(x: Int, y: Int): Boolean {
        return x >= 3 && y >= 3
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return ModRecipeSerializers.ARROW_BUNDLE.get()
    }

}