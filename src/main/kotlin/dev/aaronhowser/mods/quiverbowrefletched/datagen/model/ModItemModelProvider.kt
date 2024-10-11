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
//            ModItems.LAPIS_MAGAZINE,          //TODO: Needs special predicate
            ModItems.REDSTONE_MAGAZINE,
            ModItems.LARGE_NETHERRACK_MAGAZINE,
            ModItems.LARGE_REDSTONE_MAGAZINE,
            ModItems.ENDER_QUARTZ_CLIP
        )

        for (ammo in deferredAmmo) {
            ammo(ammo.get())
        }

        val weapons = listOf(
            ModItems.COMPACT_CROSSBOW,
            ModItems.DOUBLE_CROSSBOW,
            ModItems.BLAZE_CROSSBOW,
            ModItems.AUTO_CROSSBOW,
            ModItems.IMPROVED_AUTO_CROSSBOW,
            ModItems.COIN_TOSSER,
            ModItems.MODIFIED_COIN_TOSSER,
            ModItems.DRAGON_BOX,
            ModItems.FOUR_HEADED_DRAGON_BOX,
            ModItems.LAPIS_COIL,
            ModItems.THORN_SPITTER,
            ModItems.PROXIMITY_THORN_THROWER,
            ModItems.SUGAR_ENGINE,
            ModItems.FIREWORKS_ROCKET_LAUNCHER,
            ModItems.IMPROVED_ROCKET_LAUNCHER,
            ModItems.ARROW_MORTAR,
            ModItems.DRAGON_MORTAR,
            ModItems.SEEDLING,
            ModItems.POTATOSSER,
            ModItems.SNOW_CANNON,
            ModItems.ENDER_RIFLE,
            ModItems.FROST_LANCER,
            ModItems.OBSIDIAN_SPLINTER_PISTOL,
            ModItems.OBSIDIAN_SPEAR_RIFLE,
            ModItems.OBSIDIAN_WITHER_RIFLE,
            ModItems.FEN_FIRE,
            ModItems.FLINT_DUSTER,
            ModItems.LIGHTNING_RED,
            ModItems.SUNRAY,
            ModItems.POWDER_KNUCKLE,
            ModItems.MODIFIED_POWDER_KNUCKLE,
            ModItems.NETHER_BELLOWS,
            ModItems.REDSTONE_SPRAYER,
            ModItems.SOUL_CAIRN,
            ModItems.AQUA_ACCELERATOR,
            ModItems.SILKEN_SPINNER,
            ModItems.SEED_SWEEPER,
            ModItems.RAY_OF_HOPE,
            ModItems.ENDER_RAIL_ACCELERATOR,
            ModItems.ARMS_ASSISTANT_TARGETING_HELPER,
            ModItems.HIDDEN_ENDER_PISTOL,
        )

        for (weapon in weapons) {
            weapon(weapon.get())
        }

        pullingItem(ModItems.ENDER_BOW.get())
        pullingItem(ModItems.BOW_WITH_QUIVER.get())

    }

    private fun pullingItem(item: Item) {
        val itemRl = BuiltInRegistries.ITEM.getKey(item)

        var mainModel = getBuilder("item/${itemRl.path}")
            .parent(ModelFile.UncheckedModelFile(mcLoc("item/bow")))
            .texture("layer0", modLoc("item/weapon/${itemRl.path}"))

        for (pullStage in 0..2) {
            val textureLoc = modLoc("item/weapon/${itemRl.path}_pulling_$pullStage")

            val pullModel = getBuilder(itemRl.toString() + "_pulling_" + pullStage)
                .parent(ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", textureLoc)

            mainModel = mainModel
                .override()
                .predicate(mcLoc("pull"), 1f)
                .predicate(
                    mcLoc("pulling"), when (pullStage) {
                        0 -> 0f
                        1 -> 0.65f
                        2 -> 0.9f
                        else -> 0f
                    }
                )
                .model(pullModel)
                .end()
        }

    }

}