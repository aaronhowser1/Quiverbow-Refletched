package dev.aaronhowser.mods.quiverbowrefletched.datagen.model

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import dev.aaronhowser.mods.quiverbowrefletched.util.OtherUtil
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

    companion object {
        val isPulling = OtherUtil.modResource("is_pulling")
        val pullAmount = OtherUtil.modResource("pull_amount")
        val isEmpty = OtherUtil.modResource("is_empty")
        val percentFull = OtherUtil.modResource("percent_full")
    }

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

        val basicItems = listOf(
            ModItems.PACKED_UP_ARMS_ASSISTANT,
            ModItems.SUGAR_ENGINE_BARREL,
            ModItems.SUGAR_ENGINE_MAIN_BODY
        )

        for (item in basicItems) {
            basicItem(item.get())
        }

        val deferredAmmo = listOf(
            ModItems.ARROW_BUNDLE,
            ModItems.ROCKET_BUNDLE,
            ModItems.SUGAR_ROD_CLIP,
            ModItems.BIG_ROCKET,
            ModItems.COLD_IRON_CLIP,
            ModItems.BOX_FLINT_DUST,
        )

        for (ammo in deferredAmmo) {
            ammo(ammo.get())
        }

        lapisMagazine()

        val basicWeapons = listOf(
            ModItems.ARMS_ASSISTANT_TARGETING_HELPER,
        )

        for (weapon in basicWeapons) {
            weapon(weapon.get())
        }

        val pullingItems = listOf(
            ModItems.ENDER_BOW,
            ModItems.BOW_WITH_QUIVER
        )

        for (item in pullingItems) {
            pullingItem(item.get())
        }

        val possiblyEmptyAmmo = listOf(
            ModItems.GOLD_MAGAZINE,
            ModItems.LARGE_NETHERRACK_MAGAZINE,
            ModItems.LARGE_REDSTONE_MAGAZINE,
            ModItems.OBSIDIAN_MAGAZINE,
            ModItems.REDSTONE_MAGAZINE,
            ModItems.SEED_JAR,
            ModItems.SUGAR_ROD_CLIP,
            ModItems.THORN_MAGAZINE,
            ModItems.ENDER_QUARTZ_CLIP
        )

        for (item in possiblyEmptyAmmo) {
            ammoCanBeEmpty(item.get())
        }

        val possiblyEmptyWeapons = listOf(
            ModItems.ARROW_MORTAR,
            ModItems.AQUA_ACCELERATOR,
            ModItems.AUTO_CROSSBOW,
            ModItems.IMPROVED_AUTO_CROSSBOW,
            ModItems.BLAZE_CROSSBOW,
            ModItems.COIN_TOSSER,
            ModItems.COMPACT_CROSSBOW,
            ModItems.DOUBLE_CROSSBOW,
            ModItems.DRAGON_BOX,
            ModItems.DRAGON_MORTAR,
            ModItems.ENDER_RAIL_ACCELERATOR,
            ModItems.ENDER_RIFLE,
            ModItems.FEN_FIRE,
            ModItems.FIREWORKS_ROCKET_LAUNCHER,
            ModItems.FLINT_DUSTER,
            ModItems.FOUR_HEADED_DRAGON_BOX,
            ModItems.FROST_LANCER,
            ModItems.HIDDEN_ENDER_PISTOL,
            ModItems.IMPROVED_ROCKET_LAUNCHER,
            ModItems.LAPIS_COIL,
            ModItems.LIGHTNING_RED,
            ModItems.NETHER_BELLOWS,
            ModItems.OBSIDIAN_SPEAR_RIFLE,
            ModItems.OBSIDIAN_SPLINTER_PISTOL,
            ModItems.OBSIDIAN_WITHER_RIFLE,
            ModItems.POTATOSSER,
            ModItems.POWDER_KNUCKLE,
            ModItems.MODIFIED_COIN_TOSSER,
            ModItems.MODIFIED_POWDER_KNUCKLE,
            ModItems.RAY_OF_HOPE,
            ModItems.REDSTONE_SPRAYER,
            ModItems.SEED_SWEEPER,
            ModItems.SEEDLING,
            ModItems.SILKEN_SPINNER,
            ModItems.SNOW_CANNON,
            ModItems.SOUL_CAIRN,
            ModItems.SUGAR_ENGINE,
            ModItems.SUNRAY,
            ModItems.THORN_SPITTER,
            ModItems.PROXIMITY_THORN_THROWER
        )

        for (item in possiblyEmptyWeapons) {
            weaponCanBeEmpty(item.get())
        }

    }

    private fun lapisMagazine() {
        val item = ModItems.LAPIS_MAGAZINE.get()
        val itemRl = BuiltInRegistries.ITEM.getKey(item)

        val textureString = "item/ammo/lapis_magazine_"

        var emptyModel = getBuilder(itemRl.toString())
            .parent(ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc(textureString + "0"))

        for (filledStage in 1..6) {
            val textureLoc = modLoc(textureString + filledStage)

            val filledModel = getBuilder(itemRl.toString() + "_filled_" + filledStage)
                .parent(ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", textureLoc)

            emptyModel = emptyModel
                .override()
                .predicate(percentFull, filledStage.toFloat() / 6)
                .model(filledModel)
                .end()
        }

    }

    private fun weaponCanBeEmpty(item: Item) {
        val itemRl = BuiltInRegistries.ITEM.getKey(item)

        val emptyModel = getBuilder(itemRl.toString() + "_empty")
            .parent(ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/weapon/${itemRl.path}_empty"))

        weapon(item)
            .override()
            .predicate(isEmpty, 1f)
            .model(emptyModel)
            .end()
    }

    private fun ammoCanBeEmpty(item: Item) {
        val itemRl = BuiltInRegistries.ITEM.getKey(item)

        val emptyModel = getBuilder(itemRl.toString() + "_empty")
            .parent(ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/ammo/${itemRl.path}_empty"))

        ammo(item)
            .override()
            .predicate(isEmpty, 1f)
            .model(emptyModel)
            .end()
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

            var tempModel = mainModel
                .override()
                .predicate(isPulling, 1f)

            if (pullStage != 0) {
                tempModel = tempModel
                    .predicate(
                        pullAmount,
                        when (pullStage) {
                            1 -> 0.65f
                            2 -> 0.9f
                            else -> error("Invalid pull stage")
                        }
                    )
            }

            mainModel = tempModel
                .model(pullModel)
                .end()
        }

    }

}