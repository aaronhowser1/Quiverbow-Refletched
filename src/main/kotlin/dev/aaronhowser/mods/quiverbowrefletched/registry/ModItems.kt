package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.AdvancedAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.ArrowBundleItem
import dev.aaronhowser.mods.quiverbowrefletched.item.ammo.BasicAmmoClipItem
import dev.aaronhowser.mods.quiverbowrefletched.item.weapon.*
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {

    val ITEM_REGISTRY: DeferredRegister.Items =
        DeferredRegister.createItems(QuiverBowRefletched.ID)

    val ARROW_BUNDLE: DeferredItem<ArrowBundleItem> =
        register("arrow_bundle") { ArrowBundleItem() }
    val ROCKET_BUNDLE =
        basic("rocket_bundle")
    val SUGAR_ROD_CLIP: DeferredItem<BasicAmmoClipItem> =
        register("sugar_rod_clip") { BasicAmmoClipItem.SUGAR }
    val BIG_ROCKET =
        basic("big_rocket")
    val COLD_IRON_CLIP =
        basic("cold_iron_clip")
    val BOX_FLINT_DUST: DeferredItem<Item> =
        register("box_flint_dust") { Item(Item.Properties().stacksTo(8)) }
    val SEED_JAR: DeferredItem<AdvancedAmmoClipItem> =
        register("seed_jar") { AdvancedAmmoClipItem.SEED_JAR }
    val OBSIDIAN_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("obsidian_magazine") { BasicAmmoClipItem.OBSIDIAN }
    val GOLD_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("gold_magazine") { BasicAmmoClipItem.GOLD }
    val THORN_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("thorn_magazine") { BasicAmmoClipItem.THORN }
    val LAPIS_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("lapis_magazine") { BasicAmmoClipItem.LAPIS }
    val REDSTONE_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("redstone_magazine") { BasicAmmoClipItem.REDSTONE }
    val LARGE_NETHERRACK_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("large_netherrack_magazine") { BasicAmmoClipItem.LARGE_NETHERRACK }
    val LARGE_REDSTONE_MAGAZINE: DeferredItem<BasicAmmoClipItem> =
        register("large_redstone_magazine") { BasicAmmoClipItem.LARGE_REDSTONE }
    val ENDER_QUARTZ_CLIP: DeferredItem<BasicAmmoClipItem> =
        register("ender_quartz_clip") { BasicAmmoClipItem.ENDER_QUARTZ }

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
        register("sugar_engine") { GenericClipHoldingItem(SUGAR_ROD_CLIP.get()) }
    val FIREWORKS_ROCKET_LAUNCHER =
        register("fireworks_rocket_launcher") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val IMPROVED_ROCKET_LAUNCHER =
        register("improved_rocket_launcher") { GenericAmmoHoldingItem(maxAmmo = 1) }
    val ARROW_MORTAR: DeferredItem<ArrowMortar> =
        register("arrow_mortar") { ArrowMortar() }
    val DRAGON_MORTAR =
        register("dragon_mortar") { GenericAmmoHoldingItem(maxAmmo = 8) }
    val SEEDLING =
        register("seedling") { GenericAmmoHoldingItem(1) }
    val POTATOSSER =
        register("potatosser") { GenericAmmoHoldingItem(maxAmmo = 14) }
    val SNOW_CANNON =
        register("snow_cannon") { GenericAmmoHoldingItem(maxAmmo = 64) }
    val BOW_WITH_QUIVER: DeferredItem<BowWithQuiver> =
        register("bow_with_quiver") { BowWithQuiver() }
    val ENDER_BOW: DeferredItem<EnderBow> =
        register("ender_bow") { EnderBow() }
    val ENDER_RIFLE: DeferredItem<BasicAmmoUsingProjectileWeapon> =
        register("ender_rifle") { BasicAmmoUsingProjectileWeapon.ENDER_RIFLE }
    val FROST_LANCER =
        register("frost_lancer") { GenericAmmoHoldingItem(maxAmmo = 4) }
    val OBSIDIAN_SPLINTER_PISTOL =
        register("obsidian_splinter_pistol") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val OBSIDIAN_SPEAR_RIFLE =
        register("obsidian_spear_rifle") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val OBSIDIAN_WITHER_RIFLE =
        register("obsidian_wither_rifle") { GenericAmmoHoldingItem(maxAmmo = 16) }
    val FEN_FIRE: DeferredItem<BasicAmmoUsingProjectileWeapon> =
        register("fen_fire") { BasicAmmoUsingProjectileWeapon.FEN_FIRE }
    val FLINT_DUSTER: DeferredItem<FlintDuster> =
        register("flint_duster") { FlintDuster() }
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
    val AQUA_ACCELERATOR: DeferredItem<AquaAccelerator> =
        register("aqua_accelerator") { AquaAccelerator() }
    val SILKEN_SPINNER: DeferredItem<BasicAmmoUsingProjectileWeapon> =
        register("silken_spinner") { BasicAmmoUsingProjectileWeapon.SILKEN_SPINNER }
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

    val SUGAR_ENGINE_MAIN_BODY: DeferredItem<Item> =
        basic("sugar_engine_main_body")
    val SUGAR_ENGINE_BARREL: DeferredItem<Item> =
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