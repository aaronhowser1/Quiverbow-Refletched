package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.recipe.InsertClipRecipe
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModRecipeSerializers {

    val RECIPE_SERIALIZERS_REGISTRY: DeferredRegister<RecipeSerializer<*>> =
        DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, QuiverBowRefletched.ID)

    val INSERT_CLIP: DeferredHolder<RecipeSerializer<*>, SimpleCraftingRecipeSerializer<InsertClipRecipe>> =
        RECIPE_SERIALIZERS_REGISTRY.register("insert_clip", Supplier { SimpleCraftingRecipeSerializer { InsertClipRecipe() } })

}