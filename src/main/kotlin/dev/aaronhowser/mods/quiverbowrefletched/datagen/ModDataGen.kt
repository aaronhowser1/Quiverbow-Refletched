package dev.aaronhowser.mods.quiverbowrefletched.datagen

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.datagen.model.ModBlockStateProvider
import dev.aaronhowser.mods.quiverbowrefletched.datagen.model.ModItemModelProvider
import dev.aaronhowser.mods.quiverbowrefletched.datagen.tag.ModBlockTagsProvider
import dev.aaronhowser.mods.quiverbowrefletched.datagen.tag.ModEntityTypeTagsProvider
import dev.aaronhowser.mods.quiverbowrefletched.datagen.tag.ModItemTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.data.event.GatherDataEvent
import java.util.concurrent.CompletableFuture

@EventBusSubscriber(
    modid = QuiverBowRefletched.ID,
    bus = EventBusSubscriber.Bus.MOD
)
object ModDataGen {

    @SubscribeEvent
    fun onGatherData(event: GatherDataEvent) {
        val generator: DataGenerator = event.generator
        val output: PackOutput = generator.packOutput
        val existingFileHelper: ExistingFileHelper = event.existingFileHelper
        val lookupProvider: CompletableFuture<HolderLookup.Provider> = event.lookupProvider

        val languageProvider = generator.addProvider(event.includeClient(), ModLanguageProvider(output))

        val itemModelProvider = generator.addProvider(
            event.includeClient(),
            ModItemModelProvider(output, existingFileHelper)
        )
        val blockModelProvider = generator.addProvider(
            event.includeClient(),
            ModBlockStateProvider(output, existingFileHelper)
        )

        val recipeProvider = generator.addProvider(
            event.includeServer(),
            ModRecipeProvider(output, lookupProvider)
        )

        val blockTagsProvider = generator.addProvider(
            event.includeServer(),
            ModBlockTagsProvider(output, lookupProvider, existingFileHelper)
        )

        val itemTagsProvider = generator.addProvider(
            event.includeServer(),
            ModItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper)
        )

        val entityTypeTagProvider = generator.addProvider(
            event.includeServer(),
            ModEntityTypeTagsProvider(output, lookupProvider, existingFileHelper)
        )

    }

}