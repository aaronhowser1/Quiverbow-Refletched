package dev.aaronhowser.mods.quiverbowrefletched.datagen.model

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
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

    }

}