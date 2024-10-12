package dev.aaronhowser.mods.quiverbowrefletched.datagen.tag

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModItemTagsProvider(
    pOutput: PackOutput,
    pLookupProvider: CompletableFuture<HolderLookup.Provider>,
    pBlockTags: CompletableFuture<TagLookup<Block>>,
    existingFileHelper: ExistingFileHelper?
) : ItemTagsProvider(pOutput, pLookupProvider, pBlockTags, QuiverBowRefletched.ID, existingFileHelper) {

    companion object {
        private fun create(id: String): TagKey<Item> {
            return ItemTags.create(OtherUtil.modResource(id))
        }

        val AMMO_SUGAR_ENGINE = create("ammo/sugar_engine")
        val AMMO_SEED_JAR = create("ammo/seed_jar")
        val AMMO_OBSIDIAN = create("ammo/obsidian")
        val AMMO_GOLD_NUGGET = create("ammo/gold_nugget")
        val AMMO_THORN = create("ammo/thorn")
        val AMMO_LAPIS = create("ammo/lapis")
        val AMMO_REDSTONE = create("ammo/redstone")
        val AMMO_LARGE_NETHERRACK = create("ammo/large_netherrack")
        val AMMO_LARGE_REDSTONE = create("ammo/large_redstone")
        val AMMO_QUARTZ = create("ammo/quartz")
    }

    override fun addTags(p0: HolderLookup.Provider) {
        this.tag(AMMO_SUGAR_ENGINE)
            .addTags(Tags.Items.CROPS_SUGAR_CANE)

        this.tag(AMMO_SEED_JAR)
            .addTags(Tags.Items.SEEDS)

        this.tag(AMMO_OBSIDIAN)
            .addTags(Tags.Items.OBSIDIANS)

        this.tag(AMMO_GOLD_NUGGET)
            .addTags(Tags.Items.NUGGETS_GOLD)

        this.tag(AMMO_THORN)
            .addTags(Tags.Items.CROPS_CACTUS)

        this.tag(AMMO_LAPIS)
            .addTags(Tags.Items.GEMS_LAPIS)

        this.tag(AMMO_REDSTONE)
            .addTags(Tags.Items.DUSTS_REDSTONE)

        this.tag(AMMO_LARGE_NETHERRACK)
            .addTags(Tags.Items.NETHERRACKS)

        this.tag(AMMO_LARGE_REDSTONE)
            .addTags(Tags.Items.STORAGE_BLOCKS_REDSTONE)

        this.tag(AMMO_QUARTZ)
            .addTags(Tags.Items.GEMS_QUARTZ)
    }
}