package dev.aaronhowser.mods.quiverbowrefletched.datagen

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.Tags
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : RecipeProvider(output, lookupProvider) {

    override fun buildRecipes(recipeOutput: RecipeOutput) {

        for (shapedRecipe in shapedRecipes) {
            shapedRecipe.save(recipeOutput)
        }

    }

    private fun startRecipe(result: ItemLike): ShapedRecipeBuilder {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
    }

    private val shapedRecipes = listOf(
        startRecipe(ModItems.ARROW_BUNDLE)
            .pattern("AAA")
            .pattern("ASA")
            .pattern("AAA")
            .define('A', Items.ARROW)
            .define('S', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(Items.ARROW)),
        startRecipe(ModItems.ROCKET_BUNDLE)
            .pattern("RRR")
            .pattern("RSR")
            .pattern("RRR")
            .define('R', Items.FIREWORK_ROCKET)
            .define('S', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(Items.FIREWORK_ROCKET)),
        startRecipe(ModItems.SUGAR_ROD_CLIP)
            .pattern("P P")
            .pattern("P P")
            .pattern("PIP")
            .define('P', ItemTags.PLANKS)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(Items.SUGAR_CANE)),
        startRecipe(ModItems.SUGAR_ENGINE_MAIN_BODY)
            .pattern("RIR")
            .pattern("ROR")
            .pattern("TPN")
            .define('R', Items.REPEATER)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('O', Tags.Items.OBSIDIANS)
            .define('T', Items.TRIPWIRE_HOOK)
            .define('P', ItemTags.PLANKS)
            .define('N', Items.PISTON)
            .unlockedBy("has_item", has(Items.SUGAR_CANE)),
        startRecipe(ModItems.SUGAR_ENGINE_BARREL)
            .pattern("I I")
            .pattern("IPI")
            .pattern("ISI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.PISTON)
            .define('S', Items.STICKY_PISTON)
            .unlockedBy("has_item", has(Items.SUGAR_CANE)),
        startRecipe(ModItems.BIG_ROCKET)
            .pattern("LPP")
            .pattern("PTP")
            .pattern("PPS")
            .define('L', ItemTags.PLANKS)
            .define('P', Items.PAPER)
            .define('T', Items.TNT)
            .define('S', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(Items.FIREWORK_ROCKET)),
        startRecipe(ModItems.COLD_IRON_CLIP)
            .pattern("IIC")
            .pattern("IIC")
            .pattern("CCS")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('C', Items.ICE)
            .define('S', Tags.Items.SLIME_BALLS)
            .unlockedBy("has_item", has(Items.ICE)),
        startRecipe(ModItems.BOX_FLINT_DUST)
            .pattern("FFF")
            .pattern("FFF")
            .pattern("FFP")
            .define('F', Items.FLINT)
            .define('P', ItemTags.PLANKS)
            .unlockedBy("has_item", has(Items.FLINT)),
        startRecipe(ModItems.SEED_JAR)
            .pattern("GBG")
            .pattern("G G")
            .pattern("GIG")
            .define('G', Tags.Items.GLASS_PANES_COLORLESS)
            .define('B', ItemTags.WOODEN_BUTTONS)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(Items.WHEAT_SEEDS)),
        startRecipe(ModItems.OBSIDIAN_MAGAZINE)
            .pattern("I I")
            .pattern("I I")
            .pattern("IOI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('O', Tags.Items.OBSIDIANS)
            .unlockedBy("has_item", has(Items.OBSIDIAN)),
        startRecipe(ModItems.GOLD_MAGAZINE)
            .pattern("I I")
            .pattern("I I")
            .pattern("IGI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('G', Tags.Items.INGOTS_GOLD)
            .unlockedBy("has_item", has(Items.GOLD_INGOT)),
        startRecipe(ModItems.THORN_MAGAZINE)
            .pattern("L L")
            .pattern("L L")
            .pattern("LIL")
            .define('L', Tags.Items.LEATHERS)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(Items.LEATHER)),
        startRecipe(ModItems.LAPIS_MAGAZINE)
            .pattern("P P")
            .pattern("P P")
            .pattern("PLP")
            .define('P', Tags.Items.GLASS_PANES_COLORLESS)
            .define('L', Tags.Items.GEMS_LAPIS)
            .unlockedBy("has_item", has(Items.LAPIS_LAZULI)),
    )

}