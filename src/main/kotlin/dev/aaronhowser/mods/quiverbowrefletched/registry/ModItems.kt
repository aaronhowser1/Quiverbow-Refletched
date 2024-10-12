package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.AmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.base.AmmoClipHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.EnderBowItem
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.GenericAmmoClipHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.GenericAmmoHoldingItem
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.PowderKnuckle
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {

    val ITEM_REGISTRY: DeferredRegister.Items = DeferredRegister.createItems(QuiverBowRefletched.ID)

    val COMPACT_CROSSBOW =
        register("compact_crossbow") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val DOUBLE_CROSSBOW =
        register("double_crossbow") { GenericAmmoHoldingItem(maxAmmo = 2) }
    val BLAZE_CROSSBOW =
        register("blaze_crossbow") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val AUTO_CROSSBOW =
        register("auto_crossbow") { GenericAmmoHoldingItem(maxAmmo = 8) }
    val IMPROVED_AUTO_CROSSBOW =
        register("auto_crossbow_improved") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val COIN_TOSSER =
        register("coin_tosser") { GenericAmmoHoldingItem(maxAmmo = 72) }
    val MODIFIED_COIN_TOSSER =
        register("coin_tosser_modified") { GenericAmmoHoldingItem(maxAmmo = 72) }
    val DRAGON_BOX =
        register("dragon_box") { GenericAmmoHoldingItem(maxAmmo = 64) }
    val FOUR_HEADED_DRAGON_BOX =
        register("four_headed_dragon_box") { GenericAmmoHoldingItem(maxAmmo = 64) }
    val LAPIS_COIL =
        register("lapis_coil") { GenericAmmoHoldingItem(maxAmmo = 100) }
    val THORN_SPITTER =
        register("thorn_spitter") { GenericAmmoHoldingItem(maxAmmo = 64) }
    val PROXIMITY_THORN_THROWER =
        register("proximity_thorn_thrower") { GenericAmmoHoldingItem(maxAmmo = 64) }
    val SUGAR_ENGINE =
        register("sugar_engine") { GenericAmmoClipHoldingItem() }
    val FIREWORKS_ROCKET_LAUNCHER =
        register("fireworks_rocket_launcher") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val IMPROVED_ROCKET_LAUNCHER =
        register("improved_rocket_launcher") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val ARROW_MORTAR =
        register("arrow_mortar") { GenericAmmoHoldingItem(maxAmmo = 8) }
    val DRAGON_MORTAR =
        register("dragon_mortar") { GenericAmmoHoldingItem(maxAmmo = 8) }
    val SEEDLING =
        register("seedling") { GenericAmmoClipHoldingItem() }
    val POTATOSSER =
        register("potatosser") { GenericAmmoHoldingItem(maxAmmo = 14) }
    val SNOW_CANNON =
        register("snow_cannon") { GenericAmmoHoldingItem(maxAmmo = 64) }
    val BOW_WITH_QUIVER =
        register("bow_with_quiver") { GenericAmmoHoldingItem(maxAmmo = 256) }
    val ENDER_BOW =
        register("ender_bow") { EnderBowItem() }
    val ENDER_RIFLE =
        register("ender_rifle") { GenericAmmoHoldingItem(maxAmmo = 8) }
    val FROST_LANCER =
        register("frost_lancer") { GenericAmmoHoldingItem(maxAmmo = 4) }
    val OBSIDIAN_SPLINTER_PISTOL =
        register("obsidian_splinter_pistol") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val OBSIDIAN_SPEAR_RIFLE =
        register("obsidian_spear_rifle") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val OBSIDIAN_WITHER_RIFLE =
        register("obsidian_wither_rifle") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val FEN_FIRE =
        register("fen_fire") { GenericAmmoHoldingItem(maxAmmo = 32) }
    val FLINT_DUSTER =
        register("flint_duster") { GenericAmmoHoldingItem(maxAmmo = 256) }
    val LIGHTNING_RED =
        register("lightning_red") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val SUNRAY =
        register("sunray") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val POWDER_KNUCKLE: DeferredItem<PowderKnuckle> =
        register("powder_knuckle") { PowderKnuckle(isModified = false) }
    val MODIFIED_POWDER_KNUCKLE: DeferredItem<PowderKnuckle> =
        register("powder_knuckle_modified") { PowderKnuckle(isModified = true) }
    val NETHER_BELLOWS =
        register("nether_bellows") { GenericAmmoHoldingItem(maxAmmo = 200) }
    val REDSTONE_SPRAYER =
        register("redstone_sprayer") { GenericAmmoHoldingItem(maxAmmo = 200) }
    val SOUL_CAIRN =
        register("soul_cairn") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val AQUA_ACCELERATOR =
        register("aqua_accelerator") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val SILKEN_SPINNER =
        register("silken_spinner") { GenericAmmoHoldingItem(maxAmmo = 8) }
    val SEED_SWEEPER =
        register("seed_sweeper") { GenericAmmoHoldingItem(maxAmmo = 512) }
    val RAY_OF_HOPE =
        register("ray_of_hope") { GenericAmmoHoldingItem(maxAmmo = 320) }
    val ENDER_RAIL_ACCELERATOR =
        register("ender_rail_accelerator") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val ARMS_ASSISTANT_TARGETING_HELPER =
        register("arms_assistant_targeting_helper") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val HIDDEN_ENDER_PISTOL =
        register("hidden_ender_pistol") { GenericAmmoHoldingItem(maxAmmo = 8) }

    val ARROW_BUNDLE =
        basic("arrow_bundle")
    val ROCKET_BUNDLE =
        basic("rocket_bundle")
    val SUGAR_ROD_CLIP =
        register("sugar_rod_clip") { AmmoClipItem(maxAmmo = 200) }
    val BIG_ROCKET =
        basic("big_rocket")
    val COLD_IRON_CLIP =
        basic("cold_iron_clip")
    val BOX_FLINT_DUST =
        basic("box_flint_dust")
    val SEED_JAR =
        register("seed_jar") { AmmoClipItem(maxAmmo = 512) }
    val OBSIDIAN_MAGAZINE =
        register("obsidian_magazine") { AmmoClipItem(maxAmmo = 16) }
    val GOLD_MAGAZINE =
        register("gold_magazine") { AmmoClipItem(maxAmmo = 72) }
    val THORN_MAGAZINE =
        register("thorn_magazine")  { AmmoClipItem(maxAmmo = 64) }
    val LAPIS_MAGAZINE =
        register("lapis_magazine") { AmmoClipItem(maxAmmo = 150) }
    val REDSTONE_MAGAZINE =
        register("redstone_magazine")  { AmmoClipItem(maxAmmo = 64) }
    val LARGE_NETHERRACK_MAGAZINE =
        register("large_netherrack_magazine")  { AmmoClipItem(maxAmmo = 200) }
    val LARGE_REDSTONE_MAGAZINE =
        register("large_redstone_magazine")  { AmmoClipItem(maxAmmo = 200) }
    val ENDER_QUARTZ_CLIP =
        register("ender_quartz_clip")  { AmmoClipItem(maxAmmo = 8) }

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