package dev.aaronhowser.mods.quiverbowrefletched.datagen.model

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.client.model.generators.ModelFile
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(
    output: PackOutput,
    existingFileHelper: ExistingFileHelper
) : ItemModelProvider(output, QuiverBowRefletched.ID, existingFileHelper) {

    private fun item(item: Item, subfolder: String): ItemModelBuilder {
        val itemRl = BuiltInRegistries.ITEM.getResourceKey(item).get().location()

        return this.getBuilder(itemRl.toString())
            .parent(ModelFile.UncheckedModelFile("item/generated"))
            .texture(
                "layer0",
                ResourceLocation.fromNamespaceAndPath(
                    itemRl.namespace,
                    "item/$subfolder/${itemRl.path}"
                )
            )
    }

    private fun weapon(item: Item): ItemModelBuilder = item(item, "weapon")
    private fun ammo(item: Item): ItemModelBuilder = item(item, "ammo")

    override fun registerModels() {

        val deferredAmmo = listOf(
            ModItems.ARROW_BUNDLE,
            ModItems.ROCKET_BUNDLE,
            ModItems.SUGAR_ROD_CLIP,
            ModItems.BIG_ROCKET,
            ModItems.COLD_IRON_CLIP,
            ModItems.BOX_FLINT_DUST,
            ModItems.SEED_JAR,
            ModItems.OBSIDIAN_MAGAZINE,
            ModItems.GOLD_MAGAZINE,
            ModItems.THORN_MAGAZINE,
            ModItems.LAPIS_MAGAZINE,
            ModItems.REDSTONE_MAGAZINE,
            ModItems.LARGE_NETHERRACK_MAGAZINE,
            ModItems.LARGE_REDSTONE_MAGAZINE,
            ModItems.ENDER_QUARTZ_CLIP
        )

        for (ammo in deferredAmmo) {
            ammo(ammo.get())
        }

    }

}