package dev.aaronhowser.mods.quiverbowrefletched.datagen.tag

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.EntityTypeTagsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModEntityTypeTagsProvider(
    pOutput: PackOutput,
    pProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper?
) : EntityTypeTagsProvider(pOutput, pProvider, QuiverBowRefletched.ID, existingFileHelper) {

    companion object {
        private fun create(name: String): TagKey<EntityType<*>> = create(OtherUtil.modResource(name))
        private fun create(rl: ResourceLocation): TagKey<EntityType<*>> = TagKey.create(Registries.ENTITY_TYPE, rl)

        val WEAK_TO_SNOW_CANNON = create("weak_to_snow_cannon")
    }

    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(WEAK_TO_SNOW_CANNON)
            .add(
                EntityType.BLAZE,
                EntityType.MAGMA_CUBE
            )
    }

}