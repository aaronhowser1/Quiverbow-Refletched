package dev.aaronhowser.mods.quiverbowrefletched.datagen

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
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

        for (shapelessRecipe in shapelessRecipes) {
            shapelessRecipe.save(recipeOutput)
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
        startRecipe(ModItems.REDSTONE_MAGAZINE)
            .pattern("I I")
            .pattern("I I")
            .pattern("IRI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('R', Tags.Items.DUSTS_REDSTONE)
            .unlockedBy("has_item", has(Items.REDSTONE)),
        startRecipe(ModItems.LARGE_NETHERRACK_MAGAZINE)
            .pattern("N N")
            .pattern("N N")
            .pattern("NIN")
            .define('N', Items.NETHER_BRICK)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(Items.NETHER_BRICK)),
        startRecipe(ModItems.LARGE_REDSTONE_MAGAZINE)
            .pattern("I I")
            .pattern("I I")
            .pattern("IRI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
            .unlockedBy("has_item", has(Items.REDSTONE_BLOCK)),
        //TODO: There's four Packed Up Arms Assistant recipes? What's the difference?
        startRecipe(ModItems.ENDER_QUARTZ_CLIP)
            .pattern("QQQ")
            .pattern("IQI")
            .pattern("III")
            .define('Q', Tags.Items.GEMS_QUARTZ)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(Items.QUARTZ)),
        startRecipe(ModItems.COMPACT_CROSSBOW)
            .pattern("PTS")
            .pattern("TPS")
            .pattern("PTS")
            .define('P', ItemTags.PLANKS)
            .define('T', Items.STICK)
            .define('S', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(Items.CROSSBOW)),
        startRecipe(ModItems.BLAZE_CROSSBOW)
            .pattern("BIB")
            .pattern("ICI")
            .pattern("BIB")
            .define('B', Items.BLAZE_POWDER)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('C', ModItems.COMPACT_CROSSBOW)
            .unlockedBy("has_item", has(ModItems.COMPACT_CROSSBOW)),
        startRecipe(ModItems.AUTO_CROSSBOW)
            .pattern("III")
            .pattern("PCP")
            .pattern(" T ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.PISTON)
            .define('C', ModItems.COMPACT_CROSSBOW)
            .define('T', Items.TRIPWIRE_HOOK)
            .unlockedBy("has_item", has(ModItems.COMPACT_CROSSBOW)),
        startRecipe(ModItems.IMPROVED_AUTO_CROSSBOW)
            .pattern("III")
            .pattern("PCP")
            .pattern(" I ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.STICKY_PISTON)
            .define('C', ModItems.AUTO_CROSSBOW)
            .unlockedBy("has_item", has(ModItems.AUTO_CROSSBOW)),
        startRecipe(ModItems.COIN_TOSSER)
            .pattern("I I")
            .pattern("IPI")
            .pattern(" L ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.PISTON)
            .define('L', Items.LEVER)
            .unlockedBy("has_item", has(Tags.Items.INGOTS_GOLD)),
        startRecipe(ModItems.MODIFIED_COIN_TOSSER)
            .pattern("CS ")
            .pattern("TI ")
            .pattern("I  ")
            .define('C', ModItems.COIN_TOSSER)
            .define('S', Items.STICKY_PISTON)
            .define('T', Items.TRIPWIRE_HOOK)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(ModItems.COIN_TOSSER)),
        startRecipe(ModItems.DRAGON_BOX)
            .pattern("ISs")
            .pattern("FIs")
            .pattern("ISs")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('F', Items.FLINT_AND_STEEL)
            .define('S', Items.STICK)
            .define('s', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(ModItems.ROCKET_BUNDLE)),
        startRecipe(ModItems.FOUR_HEADED_DRAGON_BOX)
            .pattern("DDD")
            .pattern("PDP")
            .pattern("STS")
            .define('D', ModItems.DRAGON_BOX)
            .define('P', Items.PISTON)
            .define('S', Items.STICKY_PISTON)
            .define('T', Items.TRIPWIRE_HOOK)
            .unlockedBy("has_item", has(ModItems.DRAGON_BOX)),
        startRecipe(ModItems.LAPIS_COIL)
            .pattern("I I")
            .pattern("RPR")
            .pattern(" L ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('R', Items.REPEATER)
            .define('P', Items.PISTON)
            .define('L', Items.LEVER)
            .unlockedBy("has_item", has(Items.LAPIS_LAZULI)),
        startRecipe(ModItems.THORN_SPITTER)
            .pattern("BIB")
            .pattern("PHP")
            .pattern("STS")
            .define('B', Items.IRON_BARS)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.PISTON)
            .define('H', Items.HOPPER)
            .define('S', Items.STICKY_PISTON)
            .define('T', Items.TRIPWIRE_HOOK)
            .unlockedBy("has_item", has(Items.CACTUS)),
        startRecipe(ModItems.PROXIMITY_THORN_THROWER)
            .pattern("IHI")
            .pattern("BPB")
            .pattern("TSI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('H', Items.HOPPER)
            .define('B', Items.IRON_BARS)
            .define('P', Items.PISTON)
            .define('T', Items.TRIPWIRE_HOOK)
            .define('S', Items.STICKY_PISTON)
            .unlockedBy("has_item", has(Items.CACTUS)),
        startRecipe(ModItems.SUGAR_ENGINE)
            .pattern("B B")
            .pattern("B B")
            .pattern(" M ")
            .define('B', ModItems.SUGAR_ENGINE_BARREL)
            .define('M', ModItems.SUGAR_ENGINE_MAIN_BODY)
            .unlockedBy("has_item", has(Items.SUGAR_CANE)),
        startRecipe(ModItems.FIREWORKS_ROCKET_LAUNCHER)
            .pattern("P  ")
            .pattern("IP ")
            .pattern("FIP")
            .define('P', ItemTags.PLANKS)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('F', Items.FLINT_AND_STEEL)
            .unlockedBy("has_item", has(Items.FIREWORK_ROCKET)),
        startRecipe(ModItems.IMPROVED_ROCKET_LAUNCHER)
            .pattern("OOO")
            .pattern("IFI")
            .pattern("OOO")
            .define('O', Tags.Items.OBSIDIANS)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('F', ModItems.FIREWORKS_ROCKET_LAUNCHER)
            .unlockedBy("has_item", has(ModItems.FIREWORKS_ROCKET_LAUNCHER)),
        startRecipe(ModItems.ARROW_MORTAR)
            .pattern("IPI")
            .pattern("ISR")
            .pattern("TSR")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.PISTON)
            .define('S', Items.STICKY_PISTON)
            .define('R', Items.REPEATER)
            .define('T', Items.TRIPWIRE_HOOK)
            .unlockedBy("has_item", has(Items.ARROW)),
        startRecipe(ModItems.DRAGON_MORTAR)
            .pattern("IPI")
            .pattern("ISR")
            .pattern("TSF")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.PISTON)
            .define('S', Items.STICKY_PISTON)
            .define('R', Items.REPEATER)
            .define('T', Items.TRIPWIRE_HOOK)
            .define('F', Items.FLINT_AND_STEEL)
            .unlockedBy("has_item", has(Items.FIREWORK_ROCKET)),
        startRecipe(ModItems.SEEDLING)
            .pattern("SMS")
            .pattern("SMS")
            .pattern("TPS")
            .define('S', Tags.Items.CROPS_SUGAR_CANE)
            .define('M', Items.MELON)
            .define('T', Items.TRIPWIRE_HOOK)
            .define('P', Items.PISTON)
            .unlockedBy("has_item", has(Items.MELON_SEEDS)),
        startRecipe(ModItems.POTATOSSER)
            .pattern("BDB")
            .pattern("FPB")
            .pattern("TSI")
            .define('B', Items.IRON_BARS)
            .define('D', ItemTags.WOODEN_TRAPDOORS)
            .define('F', Items.FLINT_AND_STEEL)
            .define('P', Items.PISTON)
            .define('T', Items.TRIPWIRE_HOOK)
            .define('S', Items.STICKY_PISTON)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(Items.POTATO)),
        startRecipe(ModItems.SNOW_CANNON)
            .pattern("WPW")
            .pattern("WSW")
            .pattern("WTW")
            .define('W', ItemTags.WOOL)
            .define('P', Items.PISTON)
            .define('S', Items.STICKY_PISTON)
            .define('T', Items.TRIPWIRE_HOOK)
            .unlockedBy("has_item", has(Items.SNOWBALL)),
        startRecipe(ModItems.BOW_WITH_QUIVER)
            .pattern("LSs")
            .pattern("SLs")
            .pattern("LSs")
            .define('L', Tags.Items.LEATHERS)
            .define('S', Items.STICK)
            .define('s', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(Items.BOW)),
        startRecipe(ModItems.ENDER_BOW)
            .pattern("ESs")
            .pattern("SIs")
            .pattern("ESs")
            .define('E', Items.ENDER_EYE)
            .define('S', Items.STICK)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('s', Tags.Items.STRINGS)
            .unlockedBy("has_item", has(Items.BOW)),
    )

    private val shapelessRecipes = listOf(
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DOUBLE_CROSSBOW)
            .requires(ModItems.COMPACT_CROSSBOW)
            .requires(Items.STICKY_PISTON)
            .requires(Items.REPEATER)
            .unlockedBy("has_item", has(ModItems.COMPACT_CROSSBOW)),
    )

}