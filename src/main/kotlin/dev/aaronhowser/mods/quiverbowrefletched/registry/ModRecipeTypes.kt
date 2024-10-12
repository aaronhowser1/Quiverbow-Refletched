package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.recipe.InsertClipRecipe
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModRecipeTypes {

    val RECIPE_TYPES_REGISTRY: DeferredRegister<RecipeType<*>> =
        DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, QuiverBowRefletched.ID)

    val INSERT_CLIP: DeferredHolder<RecipeType<*>, RecipeType<InsertClipRecipe>> =
        registerRecipeType("insert_clip")

    private fun <T : Recipe<*>> registerRecipeType(
        name: String
    ): DeferredHolder<RecipeType<*>, RecipeType<T>> {
        return RECIPE_TYPES_REGISTRY.register(name, Supplier { object : RecipeType<T> {} })
    }

}