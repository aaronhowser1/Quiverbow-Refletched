package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.PowderKnuckle
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {

    val ITEM_REGISTRY: DeferredRegister.Items = DeferredRegister.createItems(QuiverBowRefletched.ID)

    val COMPACT_CROSSBOW =
        basic("compact_crossbow")
    val DOUBLE_CROSSBOW =
        basic("double_crossbow")
    val BLAZE_CROSSBOW =
        basic("blaze_crossbow")
    val AUTO_CROSSBOW =
        basic("auto_crossbow")
    val IMPROVED_AUTO_CROSSBOW =
        basic("auto_crossbow_improved")
    val COIN_TOSSER =
        basic("coin_tosser")
    val MODIFIED_COIN_TOSSER =
        basic("coin_tosser_modified")
    val DRAGON_BOX =
        basic("dragon_box")
    val FOUR_HEADED_DRAGON_BOX =
        basic("four_headed_dragon_box")
    val LAPIS_COIL =
        basic("lapis_coil")
    val THORN_SPITTER =
        basic("thorn_spitter")
    val PROXIMITY_THORN_THROWER =
        basic("proximity_thorn_thrower")
    val SUGAR_ENGINE =
        basic("sugar_engine")
    val FIREWORKS_ROCKET_LAUNCHER =
        basic("fireworks_rocket_launcher")
    val IMPROVED_ROCKET_LAUNCHER =
        basic("improved_rocket_launcher")
    val ARROW_MORTAR =
        basic("arrow_mortar")
    val DRAGON_MORTAR =
        basic("dragon_mortar")
    val SEEDLING =
        basic("seedling")
    val POTATOSSER =
        basic("potatosser")
    val SNOW_CANNON =
        basic("snow_cannon")
    val BOW_WITH_QUIVER =
        basic("bow_with_quiver")
    val ENDER_BOW =
        basic("ender_bow")
    val ENDER_RIFLE =
        basic("ender_rifle")
    val FROST_LANCER =
        basic("frost_lancer")
    val OBSIDIAN_SPLINTER_PISTOL =
        basic("obsidian_splinter_pistol")
    val OBSIDIAN_SPEAR_RIFLE =
        basic("obsidian_spear_rifle")
    val OBSIDIAN_WITHER_RIFLE =
        basic("obsidian_wither_rifle")
    val FEN_FIRE =
        basic("fen_fire")
    val FLINT_DUSTER =
        basic("flint_duster")
    val LIGHTNING_RED =
        basic("lightning_red")
    val SUNRAY =
        basic("sunray")
    val POWDER_KNUCKLE: DeferredItem<PowderKnuckle> =
        register("powder_knuckle") { PowderKnuckle(isModified = false) }
    val MODIFIED_POWDER_KNUCKLE: DeferredItem<PowderKnuckle> =
        register("powder_knuckle_modified") { PowderKnuckle(isModified = true) }
    val NETHER_BELLOWS =
        basic("nether_bellows")
    val REDSTONE_SPRAYER =
        basic("redstone_sprayer")
    val SOUL_CAIRN =
        basic("soul_cairn")
    val AQUA_ACCELERATOR =
        basic("aqua_accelerator")
    val SILKEN_SPINNER =
        basic("silken_spinner")
    val SEED_SWEEPER =
        basic("seed_sweeper")
    val RAY_OF_HOPE =
        basic("ray_of_hope")
    val ENDER_RAIL_ACCELERATOR =
        basic("ender_rail_accelerator")
    val ARMS_ASSISTANT_TARGETING_HELPER =
        basic("arms_assistant_targeting_helper")
    val HIDDEN_ENDER_PISTOL =
        basic("hidden_ender_pistol")

    val ARROW_BUNDLE =
        basic("arrow_bundle")
    val ROCKET_BUNDLE =
        basic("rocket_bundle")
    val SUGAR_ROD_CLIP =
        basic("sugar_rod_clip")
    val BIG_ROCKET =
        basic("big_rocket")
    val COLD_IRON_CLIP =
        basic("cold_iron_clip")
    val BOX_FLINT_DUST =
        basic("box_flint_dust")
    val SEED_JAR =
        basic("seed_jar")
    val OBSIDIAN_MAGAZINE =
        basic("obsidian_magazine")
    val GOLD_MAGAZINE =
        basic("gold_magazine")
    val THORN_MAGAZINE =
        basic("thorn_magazine")
    val LAPIS_MAGAZINE =
        basic("lapis_magazine")
    val REDSTONE_MAGAZINE =
        basic("redstone_magazine")
    val LARGE_NETHERRACK_MAGAZINE =
        basic("large_netherrack_magazine")
    val LARGE_REDSTONE_MAGAZINE =
        basic("large_redstone_magazine")
    val ENDER_QUARTZ_CLIP =
        basic("ender_quartz_clip")

    val SUGAR_ENGINE_MAIN_BODY =
        basic("sugar_engine_main_body")
    val SUGAR_ENGINE_BARREL =
        basic("sugar_engine_barrel")
    val PACKED_UP_ARMS_ASSISTANT =
        basic("packed_up_arms_assistant")

    private fun basic(id: String): DeferredItem<Item> {
        return ITEM_REGISTRY.registerSimpleItem(id)
    }

    private fun <T : Item> register(id: String, itemBuilder: (Item.Properties) -> T): DeferredItem<T> {
        return ITEM_REGISTRY.registerItem(id, itemBuilder)
    }

}