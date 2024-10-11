package dev.aaronhowser.mods.quiverbowrefletched.datagen

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.data.PackOutput
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.neoforged.neoforge.common.data.LanguageProvider

class ModLanguageProvider(
    output: PackOutput
) : LanguageProvider(output, QuiverBowRefletched.ID, "en_us") {

    companion object {
        fun String.toComponent(vararg args: Any?): MutableComponent {
            return Component.translatable(this, *args)
        }
    }

    override fun addTranslations() {
        addItem(ModItems.COMPACT_CROSSBOW, "Compact Crossbow")
        addItem(ModItems.DOUBLE_CROSSBOW, "Double Crossbow")
        addItem(ModItems.BLAZE_CROSSBOW, "Blaze Crossbow")
        addItem(ModItems.AUTO_CROSSBOW, "Auto Crossbow")
        addItem(ModItems.IMPROVED_AUTO_CROSSBOW, "Improved Auto Crossbow")
        addItem(ModItems.COIN_TOSSER, "Coin Tosser")
        addItem(ModItems.MODIFIED_COIN_TOSSER, "Modified Coin Tosser")
        addItem(ModItems.DRAGON_BOX, "Dragon Box")
        addItem(ModItems.FOUR_HEADED_DRAGON_BOX, "Four Headed Dragon Box")
        addItem(ModItems.LAPIS_COIL, "Lapis Coil")
        addItem(ModItems.THORN_SPLITTER, "Thorn Splitter")
        addItem(ModItems.PROXIMITY_THORN_THROWER, "Proximity Thorn Thrower")
        addItem(ModItems.SUGAR_ENGINE, "Sugar Engine")
        addItem(ModItems.FIREWORKS_ROCKET_LAUNCHER, "Fireworks Rocket Launcher")
        addItem(ModItems.IMPROVED_ROCKET_LAUNCHER, "Improved Rocket Launcher")
        addItem(ModItems.ARROW_MORTAR, "Arrow Mortar")
        addItem(ModItems.DRAGON_MORTAR, "Dragon Mortar")
        addItem(ModItems.SEEDLING, "Seedling")
        addItem(ModItems.POTATOSSER, "Potatosser")
        addItem(ModItems.SNOW_CANNON, "Snow Cannon")
        addItem(ModItems.BOW_WITH_QUIVER, "Bow With Quiver")
        addItem(ModItems.ENDER_BOW, "Ender Bow")
        addItem(ModItems.ENDER_RIFLE, "Ender Rifle")
        addItem(ModItems.FROST_LANCER, "Frost Lancer")
        addItem(ModItems.OBSIDIAN_SPLINTER_PISTOL, "Obsidian Splinter Pistol")
        addItem(ModItems.OBSIDIAN_SPEAR_RIFLE, "Obsidian Spear Rifle")
        addItem(ModItems.OBSIDIAN_WITHER_RIFLE, "Obsidian Wither Rifle")
        addItem(ModItems.FEN_FIRE, "Fen Fire")
        addItem(ModItems.FLINT_DUSTER, "Flint Duster")
        addItem(ModItems.LIGHTING_RED, "Lighting Red")
        addItem(ModItems.SUNRAY, "Sunray")
        addItem(ModItems.POWDER_KNUCKLE, "Powder Knuckle")
        addItem(ModItems.MODIFIED_POWDER_KNUCKLE, "Modified Powder Knuckle")
        addItem(ModItems.NETHER_BELLOWS, "Nether Bellows")
        addItem(ModItems.REDSTONE_SPRAYER, "Redstone Sprayer")
        addItem(ModItems.SOUL_CAIRN, "Soul Cairn")
        addItem(ModItems.AQUA_ACCELERATOR, "Aqua Accelerator")
        addItem(ModItems.SILKEN_SPINNER, "Silken Spinner")
        addItem(ModItems.SEED_SWEEPER, "Seed Sweeper")
        addItem(ModItems.RAY_OF_HOPE, "Ray of Hope")
        addItem(ModItems.ENDER_RAY_ACCELERATOR, "Ender Ray Accelerator")
        addItem(ModItems.ARMS_ASSISTANT_TARGETING_HELPER, "Arms Assistant Targeting Helper")
        addItem(ModItems.HIDDEN_ENDER_PISTOL, "Hidden Ender Pistol")

        addItem(ModItems.ARROW_BUNDLE, "Arrow Bundle")
        addItem(ModItems.ROCKET_BUNDLE, "Rocket Bundle")
        addItem(ModItems.SUGAR_ROD_CLIP, "Sugar Rod Clip")
        addItem(ModItems.BIG_ROCKET, "Big Rocket")
        addItem(ModItems.COLD_IRON_CLIP, "Cold Iron Clip")
        addItem(ModItems.BOX_FLINT_DUST, "Flint Dust Box")
        addItem(ModItems.SEED_JAR, "Seed Jar")
        addItem(ModItems.OBSIDIAN_MAGAZINE, "Obsidian Magazine")
        addItem(ModItems.GOLD_MAGAZINE, "Gold Magazine")
        addItem(ModItems.THORN_MAGAZINE, "Thorn Magazine")
        addItem(ModItems.LAPIS_MAGAZINE, "Lapis Magazine")
        addItem(ModItems.REDSTONE_MAGAZINE, "Redstone Magazine")
        addItem(ModItems.LARGE_NETHERRACK_MAGAZINE, "Large Netherrack Magazine")
        addItem(ModItems.LARGE_REDSTONE_MAGAZINE, "Large Redstone Magazine")
        addItem(ModItems.ENDER_QUARTZ_CLIP, "Ender Quartz Clip")

        addItem(ModItems.SUGAR_ENGINE_MAIN_BODY, "Sugar Engine Main Body")
        addItem(ModItems.SUGAR_ENGINE_BARREL, "Sugar Engine Barrel")
        addItem(ModItems.PACKED_UP_ARMS_ASSISTANT, "Pack Up Arms Assistant")
    }
}