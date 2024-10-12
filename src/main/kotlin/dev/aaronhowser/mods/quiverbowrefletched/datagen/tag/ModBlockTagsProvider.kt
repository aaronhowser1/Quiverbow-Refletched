package dev.aaronhowser.mods.quiverbowrefletched.datagen.tag

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModBlockTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper?
) : BlockTagsProvider(output, lookupProvider, QuiverBowRefletched.ID, existingFileHelper) {
    override fun addTags(p0: HolderLookup.Provider) {
        // There's literally no blocks in this mod why is this a requirement for item tags
    }
}