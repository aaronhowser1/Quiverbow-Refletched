package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.EnderBowItem
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.GenericWeapon
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.PowderKnuckle
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {

    val ITEM_REGISTRY: DeferredRegister.Items = DeferredRegister.createItems(QuiverBowRefletched.ID)

    val COMPACT_CROSSBOW =
        register("compact_crossbow") { GenericWeapon(maxAmmo = 1) }
    val DOUBLE_CROSSBOW =
        register("double_crossbow") { GenericWeapon(maxAmmo = 2) }
    val BLAZE_CROSSBOW =
        register("blaze_crossbow") { GenericWeapon(maxAmmo = 1) }
    val AUTO_CROSSBOW =
        register("auto_crossbow") { GenericWeapon(maxAmmo = 8) }
    val IMPROVED_AUTO_CROSSBOW =
        register("auto_crossbow_improved") { GenericWeapon(maxAmmo = 16) }
    val COIN_TOSSER =
        register("coin_tosser") { GenericWeapon(maxAmmo = 72) }
    val MODIFIED_COIN_TOSSER =
        register("coin_tosser_modified") { GenericWeapon(maxAmmo = 72) }
    val DRAGON_BOX =
        register("dragon_box") { GenericWeapon(maxAmmo = 64) }
    val FOUR_HEADED_DRAGON_BOX =
        register("four_headed_dragon_box") { GenericWeapon(maxAmmo = 64) }
    val LAPIS_COIL =
        register("lapis_coil") { GenericWeapon(maxAmmo = 100) }
    val THORN_SPITTER =
        register("thorn_spitter") { GenericWeapon(maxAmmo = 64) }
    val PROXIMITY_THORN_THROWER =
        register("proximity_thorn_thrower") { GenericWeapon(maxAmmo = 64) }
    val SUGAR_ENGINE =
        register("sugar_engine") { GenericWeapon(maxAmmo = 200) }
    val FIREWORKS_ROCKET_LAUNCHER =
        register("fireworks_rocket_launcher") { GenericWeapon(maxAmmo = 1) }
    val IMPROVED_ROCKET_LAUNCHER =
        register("improved_rocket_launcher") { GenericWeapon(maxAmmo = 1) }
    val ARROW_MORTAR =
        register("arrow_mortar") { GenericWeapon(maxAmmo = 8) }
    val DRAGON_MORTAR =
        register("dragon_mortar") { GenericWeapon(maxAmmo = 8) }
    val SEEDLING =
        register("seedling") { GenericWeapon(maxAmmo = 32) }
    val POTATOSSER =
        register("potatosser") { GenericWeapon(maxAmmo = 14) }
    val SNOW_CANNON =
        register("snow_cannon") { GenericWeapon(maxAmmo = 64) }
    val BOW_WITH_QUIVER =
        register("bow_with_quiver") { GenericWeapon(maxAmmo = 256) }
    val ENDER_BOW =
        register("ender_bow") { EnderBowItem() }
    val ENDER_RIFLE =
        register("ender_rifle") { GenericWeapon(maxAmmo = 8) }
    val FROST_LANCER =
        register("frost_lancer") { GenericWeapon(maxAmmo = 4) }
    val OBSIDIAN_SPLINTER_PISTOL =
        register("obsidian_splinter_pistol") { GenericWeapon(maxAmmo = 16) }
    val OBSIDIAN_SPEAR_RIFLE =
        register("obsidian_spear_rifle") { GenericWeapon(maxAmmo = 16) }
    val OBSIDIAN_WITHER_RIFLE =
        register("obsidian_wither_rifle") { GenericWeapon(maxAmmo = 16) }
    val FEN_FIRE =
        register("fen_fire") { GenericWeapon(maxAmmo = 32) }
    val FLINT_DUSTER =
        register("flint_duster") { GenericWeapon(maxAmmo = 256) }
    val LIGHTNING_RED =
        register("lightning_red") { GenericWeapon(maxAmmo = 16) }
    val SUNRAY =
        register("sunray") { GenericWeapon(maxAmmo = 1) }
    val POWDER_KNUCKLE: DeferredItem<PowderKnuckle> =
        register("powder_knuckle") { PowderKnuckle(isModified = false) }
    val MODIFIED_POWDER_KNUCKLE: DeferredItem<PowderKnuckle> =
        register("powder_knuckle_modified") { PowderKnuckle(isModified = true) }
    val NETHER_BELLOWS =
        register("nether_bellows") { GenericWeapon(maxAmmo = 200) }
    val REDSTONE_SPRAYER =
        register("redstone_sprayer") { GenericWeapon(maxAmmo = 200) }
    val SOUL_CAIRN =
        register("soul_cairn") { GenericWeapon(maxAmmo = 1) }
    val AQUA_ACCELERATOR =
        register("aqua_accelerator") { GenericWeapon(maxAmmo = 1) }
    val SILKEN_SPINNER =
        register("silken_spinner") { GenericWeapon(maxAmmo = 8) }
    val SEED_SWEEPER =
        register("seed_sweeper") { GenericWeapon(maxAmmo = 512) }
    val RAY_OF_HOPE =
        register("ray_of_hope") { GenericWeapon(maxAmmo = 320) }
    val ENDER_RAIL_ACCELERATOR =
        register("ender_rail_accelerator") { GenericWeapon(maxAmmo = 1) }
    val ARMS_ASSISTANT_TARGETING_HELPER =
        register("arms_assistant_targeting_helper") { GenericWeapon(maxAmmo = 1) }
    val HIDDEN_ENDER_PISTOL =
        register("hidden_ender_pistol") { GenericWeapon(maxAmmo = 8) }

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