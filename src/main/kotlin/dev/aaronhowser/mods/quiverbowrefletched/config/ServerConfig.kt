package dev.aaronhowser.mods.quiverbowrefletched.config

import net.neoforged.neoforge.common.ModConfigSpec
import org.apache.commons.lang3.tuple.Pair

class ServerConfig(
    private val builder: ModConfigSpec.Builder
) {

    companion object {
        private val configPair: Pair<ServerConfig, ModConfigSpec> = ModConfigSpec.Builder().configure(::ServerConfig)

        private fun ModConfigSpec.Builder.defineDouble(
            name: String,
            defaultValue: Number,
            minValue: Number = 0.0,
            maxValue: Number = Double.MAX_VALUE
        ): ModConfigSpec.DoubleValue {
            return this.defineInRange(
                name,
                defaultValue.toDouble(),
                minValue.toDouble(),
                maxValue.toDouble()
            )
        }

        private fun ModConfigSpec.Builder.defineInteger(
            name: String,
            defaultValue: Int,
            minValue: Int = 0,
            maxValue: Int = Int.MAX_VALUE
        ): ModConfigSpec.IntValue {
            return this.defineInRange(
                name,
                defaultValue,
                minValue,
                maxValue
            )
        }

        val CONFIG: ServerConfig = configPair.left
        val CONFIG_SPEC: ModConfigSpec = configPair.right

        lateinit var AQUA_ACCELERATOR_PROJECTILE_SPEED: ModConfigSpec.DoubleValue

        lateinit var ARMS_ASSIStANT_RANGE: ModConfigSpec.DoubleValue

        lateinit var ARROW_MORTAR_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var ARROW_MORTAR_RECOIL: ModConfigSpec.DoubleValue
        lateinit var ARROW_MORTAR_COOLDOWN: ModConfigSpec.IntValue
        lateinit var ARROW_MORTAR_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var ARROW_MORTAR_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var AUTO_CROSSBOW_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var AUTO_CROSSBOW_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var AUTO_CROSSBOW_COOLDOWN: ModConfigSpec.IntValue
        lateinit var AUTO_CROSSBOW_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var AUTO_CROSSBOW_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var BLAZE_CROSSBOW_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var BLAZE_CROSSBOW_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var BLAZE_CROSSBOW_BURN_TIME_AFTER_LANDING: ModConfigSpec.IntValue
        lateinit var BLAZE_CROSSBOW_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var BLAZE_CROSSBOW_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var BLAZE_CROSSBOW_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var COIN_TOSSER_DROP_NUGGET_ON_MISS: ModConfigSpec.BooleanValue
        lateinit var COIN_TOSSER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var COIN_TOSSER_RECOIL: ModConfigSpec.DoubleValue
        lateinit var COIN_TOSSER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var COIN_TOSSER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var COIN_TOSSER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var COMPACT_CROSSBOW_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var COMPACT_CROSSBOW_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var COMPACT_CROSSBOW_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var COMPACT_CROSSBOW_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var DOUBLE_CROSSBOW_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var DOUBLE_CROSSBOW_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var DOUBLE_CROSSBOW_COOLDOWN: ModConfigSpec.IntValue
        lateinit var DOUBLE_CROSSBOW_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var DOUBLE_CROSSBOW_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var DRAGON_BOX_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var DRAGON_BOX_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var DRAGON_BOX_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var DRAGON_BOX_RECOIL: ModConfigSpec.DoubleValue
        lateinit var DRAGON_BOX_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var DRAGON_BOX_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var DRAGON_BOX_COOLDOWN: ModConfigSpec.IntValue
        lateinit var DRAGON_BOX_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var DRAGON_BOX_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var DRAGON_MORTAR_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var DRAGON_MORTAR_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var DRAGON_MORTAR_RECOIL: ModConfigSpec.DoubleValue
        lateinit var DRAGON_MORTAR_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var DRAGON_MORTAR_COOLDOWN: ModConfigSpec.IntValue
        lateinit var DRAGON_MORTAR_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var DRAGON_MORTAR_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var ENDER_BOW_ZOOM_FACTOR: ModConfigSpec.DoubleValue
        lateinit var ENDER_BOW_GUIDE_FREQUENCY: ModConfigSpec.IntValue      //TODO: Clientside? Don't think it can be

        lateinit var ENDER_RAIL_ACCELERATOR_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var ENDER_RAIL_ACCELERATOR_HIT_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_SHOOT_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_RECOIL: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var ENDER_RIFLE_DAMAGE_INCREASE_PER_TICK: ModConfigSpec.DoubleValue    //TODO: IMO this should be a factor of distance traveled, not time in air
        lateinit var ENDER_RIFLE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_ZOOM_FACTOR: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_RECOIL: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_COOLDOWN: ModConfigSpec.IntValue
        lateinit var ENDER_RIFLE_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var FEN_FIRE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var FEN_FIRE_LIGHT_LIFESPAN: ModConfigSpec.IntValue
        lateinit var FEN_FIRE_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var FEN_FIRE_TARGET_GLOW_TIME: ModConfigSpec.IntValue
        lateinit var FEN_FIRE_COOLDOWN: ModConfigSpec.IntValue

        lateinit var FIREWORKS_ROCKET_LAUNCHER_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var FIREWORKS_ROCKET_LAUNCHER_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var FIREWORKS_ROCKET_LAUNCHER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var FIREWORKS_ROCKET_LAUNCHER_RECOIL: ModConfigSpec.DoubleValue
        lateinit var FIREWORKS_ROCKET_LAUNCHER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var FIREWORKS_ROCKET_LAUNCHER_TIME_UNTIL_EXPLODE: ModConfigSpec.IntValue

        lateinit var FLINT_DUSTER_RANGE: ModConfigSpec.DoubleValue
        lateinit var FLINT_DUSTER_DAMAGE: ModConfigSpec.DoubleValue

        lateinit var FOUR_HEADED_DRAGON_BOX_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var FOUR_HEADED_DRAGON_BOX_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var FOUR_HEADED_DRAGON_BOX_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var FOUR_HEADED_DRAGON_BOX_RECOIL: ModConfigSpec.DoubleValue
        lateinit var FOUR_HEADED_DRAGON_BOX_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var FOUR_HEADED_DRAGON_BOX_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var FOUR_HEADED_DRAGON_BOX_COOLDOWN: ModConfigSpec.IntValue
        lateinit var FOUR_HEADED_DRAGON_BOX_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var FOUR_HEADED_DRAGON_BOX_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var FROST_LANCER_ZOOM_FACTOR: ModConfigSpec.DoubleValue
        lateinit var FROST_LANCER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var FROST_LANCER_RECOIL: ModConfigSpec.DoubleValue
        lateinit var FROST_LANCER_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var FROST_LANCER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var FROST_LANCER_NAUSEA_DURATION: ModConfigSpec.IntValue
        lateinit var FROST_LANCER_SLOWNESS_DURATION: ModConfigSpec.IntValue
        lateinit var FROST_LANCER_SLOWNESS_STRENGTH: ModConfigSpec.IntValue
        lateinit var FROST_LANCER_FREEZING_DURATION: ModConfigSpec.IntValue
        lateinit var FROST_LANCER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var FROST_LANCER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var HIDDEN_ENDER_PISTOL_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var HIDDEN_ENDER_PISTOL_RECOIL: ModConfigSpec.DoubleValue
        lateinit var HIDDEN_ENDER_PISTOL_PROJECTILE_LIFESPAN: ModConfigSpec.IntValue
        lateinit var HIDDEN_ENDER_PISTOL_COOLDOWN: ModConfigSpec.IntValue
        lateinit var HIDDEN_ENDER_PISTOL_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var HIDDEN_ENDER_PISTOL_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue  //TODO: What if it does more damage from behind

        lateinit var IMPROVED_AUTO_CROSSBOW_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var IMPROVED_AUTO_CROSSBOW_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var IMPROVED_AUTO_CROSSBOW_COOLDOWN: ModConfigSpec.IntValue
        lateinit var IMPROVED_AUTO_CROSSBOW_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var IMPROVED_AUTO_CROSSBOW_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var IMPROVED_ROCKET_LAUNCHER_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var IMPROVED_ROCKET_LAUNCHER_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var IMPROVED_ROCKET_LAUNCHER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var IMPROVED_ROCKET_LAUNCHER_RECOIL: ModConfigSpec.DoubleValue

        lateinit var LAPIS_COIL_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var LAPIS_COIL_HUNGER_DURATION: ModConfigSpec.IntValue
        lateinit var LAPIS_COIL_NAUSEA_DURATION: ModConfigSpec.IntValue
        lateinit var LAPIS_COIL_WEAKNESS_DURATION: ModConfigSpec.IntValue
        lateinit var LAPIS_COIL_HUNGER_STRENGTH: ModConfigSpec.IntValue
        lateinit var LAPIS_COIL_WEAKNESS_STRENGTH: ModConfigSpec.IntValue
        lateinit var LAPIS_COIL_COOLDOWN: ModConfigSpec.IntValue
        lateinit var LAPIS_COIL_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var LAPIS_COIL_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var LIGHTNING_RED_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var LIGHTNING_RED_RECOIL: ModConfigSpec.DoubleValue
        lateinit var LIGHTNING_RED_COOLDOWN: ModConfigSpec.IntValue
        lateinit var LIGHTNING_RED_BEAM_DURATION: ModConfigSpec.IntValue
        lateinit var LIGHTNING_RED_PIERCING_AMOUNT: ModConfigSpec.IntValue
        lateinit var LIGHTNING_RED_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var LIGHTNING_RED_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var MODIFIED_COIN_TOSSER_DROP_NUGGET_ON_MISS: ModConfigSpec.BooleanValue
        lateinit var MODIFIED_COIN_TOSSER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var MODIFIED_COIN_TOSSER_RECOIL: ModConfigSpec.DoubleValue
        lateinit var MODIFIED_COIN_TOSSER_COOLDOWN: ModConfigSpec.DoubleValue
        lateinit var MODIFIED_COIN_TOSSER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var MODIFIED_COIN_TOSSER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var MODIFIED_POWDER_KNUCKLE_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var MODIFIED_POWDER_KNUCKLE_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var MODIFIED_POWDER_KNUCKLE_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var MODIFIED_POWDER_KNUCKLE_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var NETHER_BELLOWS_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var NETHER_BELLOWS_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var NETHER_BELLOWS_DAMAGE_PER_PROJECTILE: ModConfigSpec.DoubleValue

        lateinit var OBSIDIAN_SPEAR_RIFLE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_RECOIL: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_WITHER_DURATION: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_WITHER_STRENGTH: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_COOLDOWN: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_SPEAR_RIFLE_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var OBSIDIAN_SPLINTER_PISTOL_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_SPLINTER_PISTOL_WITHER_DURATION: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_SPLINTER_PISTOL_WITHER_STRENGTH: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_SPLINTER_PISTOL_COOLDOWN: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_SPLINTER_PISTOL_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_SPLINTER_PISTOL_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var OBSIDIAN_WITHER_RIFLE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_WITHER_RIFLE_RECOIL: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_WITHER_RIFLE_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_WITHER_RIFLE_WITHER_DURATION: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_WITHER_RIFLE_WITHER_STRENGTH: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_WITHER_RIFLE_COOLDOWN: ModConfigSpec.IntValue
        lateinit var OBSIDIAN_WITHER_RIFLE_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_WITHER_RIFLE_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_WITHER_RIFLE_MAGIC_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var OBSIDIAN_WITHER_RIFLE_MAGIC_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var POTATOSSER_DROP_POTATO_ON_MISS: ModConfigSpec.BooleanValue
        lateinit var POTATOSSER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var POTATOSSER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var POTATOSSER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var POTATOSSER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var POWDER_KNUCKLE_GRIEFING: ModConfigSpec.BooleanValue
        lateinit var POWDER_KNUCKLE_EXPLOSION_RADIUS: ModConfigSpec.DoubleValue
        lateinit var POWDER_KNUCKLE_DAMAGE_ON_EXPLODE: ModConfigSpec.DoubleValue
        lateinit var POWDER_KNUCKLE_DAMAGE_WHEN_EMPTY: ModConfigSpec.DoubleValue

        lateinit var PROXIMITY_THORN_THROWER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var PROXIMITY_THORN_THROWER_RECOIL: ModConfigSpec.DoubleValue
        lateinit var PROXIMITY_THORN_THROWER_LIFESPAN: ModConfigSpec.IntValue
        lateinit var PROXIMITY_THORN_THROWER_CHECK_FREQUENCY: ModConfigSpec.IntValue
        lateinit var PROXIMITY_THORN_THROWER_CHECK_RADIUS: ModConfigSpec.DoubleValue
        lateinit var PROXIMITY_THORN_THROWER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var PROXIMITY_THORN_THROWER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var PROXIMITY_THORN_THROWER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var RAY_OF_HOPE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue

        lateinit var REDSTONE_SPRAYER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var REDSTONE_SPRAYER_BLINDNESS_DURATION: ModConfigSpec.IntValue
        lateinit var REDSTONE_SPRAYER_WITHER_DURATION: ModConfigSpec.IntValue
        lateinit var REDSTONE_SPRAYER_WITHER_STRENGTH: ModConfigSpec.IntValue

        lateinit var SEED_SWEEPER_ACCURACY: ModConfigSpec.DoubleValue
        lateinit var SEED_SWEEPER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var SEED_SWEEPER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var SEED_SWEEPER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var SEED_SWEEPER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var SEEDLING_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var SEEDLING_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var SEEDLING_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var SILKEN_SPINNER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var SILKEN_SPINNER_COOLDOWN: ModConfigSpec.IntValue

        lateinit var SNOW_CANNON_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var SNOW_CANNON_RECOIL: ModConfigSpec.DoubleValue
        lateinit var SNOW_CANNON_COOLDOWN: ModConfigSpec.IntValue
        lateinit var SNOW_CANNON_SLOWNESS_DURATION: ModConfigSpec.IntValue
        lateinit var SNOW_CANNON_SLOWNESS_STRENGTH: ModConfigSpec.IntValue
        lateinit var SNOW_CANNON_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var SNOW_CANNON_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var SOUL_CAIRN_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var SOUL_CAIRN_RECOIL: ModConfigSpec.DoubleValue

        lateinit var SUGAR_ENGINE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var SUGAR_ENGINE_RECOIL: ModConfigSpec.DoubleValue     //TODO: Let this make you fly
        lateinit var SUGAR_ENGINE_ACCURACY: ModConfigSpec.DoubleValue
        lateinit var SUGAR_ENGINE_WIND_UP_TIME: ModConfigSpec.IntValue
        lateinit var SUGAR_ENGINE_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var SUGAR_ENGINE_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var SUNRAY_RECOIL: ModConfigSpec.DoubleValue   //TODO: Grow plants instantly
        lateinit var SUNRAY_BEAM_LIFESPAN: ModConfigSpec.IntValue
        lateinit var SUNRAY_TARGET_BURN_TIME: ModConfigSpec.IntValue
        lateinit var SUNRAY_COOLDOWN: ModConfigSpec.IntValue
        lateinit var SUNRAY_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var SUNRAY_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue
        lateinit var SUNRAY_LIGHT_LEVEL_REQUIRED: ModConfigSpec.IntValue

        lateinit var THORN_SPITTER_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var THORN_SPITTER_COOLDOWN: ModConfigSpec.IntValue
        lateinit var THORN_SPITTER_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var THORN_SPITTER_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue
    }

    init {
        aquaAccelerator()
        armsAssistant()
        arrowMortar()
        autoCrossbow()
        blazeCrossbow()
        coinTosser()
        compactCrossbow()
        doubleCrossbow()
        dragonBox()
        dragonMortar()
        enderBow()
        enderRailAccelerator()
        enderRifle()
        fenFire()
        fireworksRocketLauncher()
        flintDuster()
        fourHeadedDragonBox()
        frostLancer()
//        hiddenEnderPistol()
//        improvedAutoCrossbow()
//        improvedRocketLauncher()
//        lapisCoil()
//        lightningRed()
//        modifiedCoinTosser()
//        modifiedPowderKnuckle()
//        netherBellows()
//        obsidianSpearRifle()
//        obsidianSplinterPistol()
//        obsidianWitherRifle()
//        potatOsser()
//        powderKnuckle()
//        proximityThornThrower()
//        rayOfHope()
//        redstoneSprayer()
//        seedSweeper()
//        seedling()
//        silkenSpinner()
//        snowCannon()
//        soulCairn()
//        sugarEngine()
//        sunray()
//        thornSpitter()


        builder.build()
    }

    private fun aquaAccelerator() {
        builder
            .comment("Aqua Accelerator settings")
            .push("aqua_accelerator")

        AQUA_ACCELERATOR_PROJECTILE_SPEED = builder
            .comment("How fast should Aqua Accelerator projectiles travel?")
            .defineDouble("projectile_speed", 1.5)

        builder.pop()
    }

    private fun armsAssistant() {
        builder
            .comment("Arms Assistant settings")
            .push("arms_assistant")

        ARMS_ASSIStANT_RANGE = builder
            .comment("Arms Assistant range")
            .defineDouble("range", 32)

        builder.pop()
    }

    private fun arrowMortar() {
        builder
            .comment("Arrow Mortar settings")
            .push("arrow_mortar")

        ARROW_MORTAR_PROJECTILE_SPEED = builder
            .comment("How fast should Arrow Mortar projectiles travel?")
            .defineDouble("projectile_speed", 1.5)

        ARROW_MORTAR_RECOIL = builder
            .comment("How much recoil should the Arrow Mortar have?")
            .defineDouble("recoil", 3)

        ARROW_MORTAR_COOLDOWN = builder
            .comment("How long should the Arrow Mortar cooldown be?")
            .defineInteger("cooldown", 20)

        ARROW_MORTAR_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage Arrow Mortar can deal?")
            .defineDouble("damage_minimum", 2)

        ARROW_MORTAR_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage Arrow Mortar can deal?")
            .defineDouble("damage_maximum", 10)

        builder.pop()
    }

    private fun autoCrossbow() {
        builder
            .comment("Auto Crossbow settings")
            .push("auto_crossbow")

        AUTO_CROSSBOW_PROJECTILE_SPEED = builder
            .comment("How fast should Auto Crossbow projectiles travel?")
            .defineDouble("projectile_speed", 2.5)

        AUTO_CROSSBOW_KNOCKBACK = builder
            .comment("How much knockback should the Auto Crossbow have?")
            .defineDouble("recoil", 1)

        AUTO_CROSSBOW_COOLDOWN = builder
            .comment("How long should the Auto Crossbow cooldown be?")
            .defineInteger("cooldown", 10)

        AUTO_CROSSBOW_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage Auto Crossbow can deal?")
            .defineDouble("damage_minimum", 10)

        AUTO_CROSSBOW_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage Auto Crossbow can deal?")
            .defineDouble("damage_maximum", 16)

        builder.pop()
    }

    private fun blazeCrossbow() {
        builder
            .comment("Blaze Crossbow settings")
            .push("blaze_crossbow")

        BLAZE_CROSSBOW_PROJECTILE_SPEED = builder
            .comment("How fast should Blaze Crossbow projectiles travel?")
            .defineDouble("projectile_speed", 3)

        BLAZE_CROSSBOW_KNOCKBACK = builder
            .comment("How much knockback should the Blaze Crossbow have?")
            .defineDouble("recoil", 2)

        BLAZE_CROSSBOW_BURN_TIME_AFTER_LANDING = builder
            .comment("How long should projectiles stay burning after landing on the ground?")
            .defineInteger("burn_time_after_landing", 10 * 20)

        BLAZE_CROSSBOW_TARGET_BURN_TIME = builder
            .comment("How long should targets burn for?")
            .defineInteger("target_burn_time", 15 * 20)

        BLAZE_CROSSBOW_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage Blaze Crossbow can deal?")
            .defineDouble("damage_minimum", 20)

        BLAZE_CROSSBOW_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage Blaze Crossbow can deal?")
            .defineDouble("damage_maximum", 30)

        builder.pop()
    }

    private fun coinTosser() {
        builder
            .comment("Coin Tosser settings")
            .push("coin_tosser")

        COIN_TOSSER_DROP_NUGGET_ON_MISS = builder
            .comment("Should projectiles that miss drop Gold Nuggets?")
            .define("drop_nuggets", true)

        COIN_TOSSER_PROJECTILE_SPEED = builder
            .comment("How fast should Coin Tosser projectiles be?")
            .defineDouble("projectile_speed", 2.5)

        COIN_TOSSER_RECOIL = builder
            .comment("How much recoil should the Coin Tosser have?")
            .defineDouble("recoil", 1)

        COIN_TOSSER_COOLDOWN = builder
            .comment("How long should the Coin Tosser cooldown be?")
            .defineInteger("cooldown", 15)

        COIN_TOSSER_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage each projectile should deal?")
            .defineDouble("damage_minimum", 1)

        COIN_TOSSER_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage each projectile should deal?")
            .defineDouble("damage_maximum", 3)

        builder.pop()
    }

    private fun compactCrossbow() {
        builder
            .comment("Compact Crossbow settings")
            .push("compact_crossbow")

        COMPACT_CROSSBOW_PROJECTILE_SPEED = builder
            .comment("How fast should Compact Crossbow projectiles be?")
            .defineDouble("projectile_speed", 2.5)

        COMPACT_CROSSBOW_KNOCKBACK = builder
            .comment("How much knockback should the Compact Crossbow have?")
            .defineDouble("knockback", 2)

        COMPACT_CROSSBOW_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage a Compact Crossbow can deal?")
            .defineDouble("damage_minimum", 14)

        COMPACT_CROSSBOW_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage a Compact Crossbow can deal?")
            .defineDouble("damage_maximum", 20)

        builder.pop()
    }

    private fun doubleCrossbow() {
        builder
            .comment("Double Crossbow settings")
            .push("double_crossbow")

        DOUBLE_CROSSBOW_PROJECTILE_SPEED = builder
            .comment("How fast should Double Crossbow projectiles be?")
            .defineDouble("projectile_speed", 2.5)

        DOUBLE_CROSSBOW_KNOCKBACK = builder
            .comment("How much knockback should the Double Crossbow have?")
            .defineDouble("knockback", 2)

        DOUBLE_CROSSBOW_COOLDOWN = builder
            .comment("How long should the Double Crossbow cooldown be?")
            .defineInteger("cooldown", 25)

        DOUBLE_CROSSBOW_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage a Double Crossbow can deal?")
            .defineDouble("damage_minimum", 14)

        DOUBLE_CROSSBOW_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage a Double Crossbow can deal?")
            .defineDouble("damage_maximum", 20)

        builder.pop()
    }

    private fun dragonBox() {
        builder
            .comment("Dragon Box settings")
            .push("dragon_box")

        DRAGON_BOX_GRIEFING = builder
            .comment("Can the Dragon Box destroy terrain when used by players?")
            .define("griefing", true)

        DRAGON_BOX_EXPLOSION_RADIUS = builder
            .comment("How large should Dragon Box explosions be? 4 is the same as a TNT block.")
            .defineDouble("explosion_radius", 1)

        DRAGON_BOX_PROJECTILE_SPEED = builder
            .comment("How fast should Dragon Box projectiles be?")
            .defineDouble("projectile_speed", 1.3)

        DRAGON_BOX_RECOIL = builder
            .comment("How much recoil should the Dragon Box have?")
            .defineDouble("recoil", 1)

        DRAGON_BOX_KNOCKBACK = builder
            .comment("How much knockback should the Dragon Box have?")
            .defineDouble("knockback", 2)

        DRAGON_BOX_TARGET_BURN_TIME = builder
            .comment("How long should targets burn for?")
            .defineInteger("target_burn_time", 6 * 20)

        DRAGON_BOX_COOLDOWN = builder
            .comment("How long should the Dragon Box cooldown be?")
            .defineInteger("cooldown", 10)

        DRAGON_BOX_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage a Dragon Box can deal?")
            .defineDouble("damage_minimum", 4)

        DRAGON_BOX_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage a Dragon Box can deal?")
            .defineDouble("damage_maximum", 6)

        builder.pop()
    }

    private fun dragonMortar() {
        builder
            .comment("Dragon Mortar settings")
            .push("dragon_mortar")

        DRAGON_MORTAR_EXPLOSION_RADIUS = builder
            .comment("How large should Dragon Mortar explosions be? 4 is the same as a TNT block.")
            .defineDouble("explosion_radius", 1)

        DRAGON_MORTAR_PROJECTILE_SPEED = builder
            .comment("How fast should Dragon Mortar projectiles be?")
            .defineDouble("projectile_speed", 1.5)

        DRAGON_MORTAR_RECOIL = builder
            .comment("How much recoil should the Dragon Mortar have?")
            .defineDouble("recoil", 3)

        DRAGON_MORTAR_TARGET_BURN_TIME = builder
            .comment("How long should targets burn for?")
            .defineInteger("target_burn_time", 6 * 20)

        DRAGON_MORTAR_COOLDOWN = builder
            .comment("How long should the Dragon Mortar cooldown be?")
            .defineInteger("cooldown", 20, 0)

        DRAGON_MORTAR_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage a Dragon Mortar can deal?")
            .defineDouble("damage_minimum", 4)

        DRAGON_MORTAR_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage a Dragon Mortar can deal?")
            .defineDouble("damage_maximum", 6)

        builder.pop()
    }

    private fun enderBow() {
        builder
            .comment("Ender Bow settings")
            .push("ender_bow")

        ENDER_BOW_ZOOM_FACTOR = builder
            .comment("What should the zoom factor be for the Ender Bow? Your FOV is multiplied by this value.")
            .defineDouble("zoom_factor", 0.3)

        ENDER_BOW_GUIDE_FREQUENCY = builder
            .comment("How often should the Ender Bow shoot out a guiding projectile??")
            .defineInteger("guide_frequency", 5)

        builder.pop()
    }

    private fun enderRailAccelerator() {
        builder
            .comment("Ender Rail Accelerator settings")
            .push("ender_rail_accelerator")

        ENDER_RAIL_ACCELERATOR_GRIEFING = builder
            .comment("Can the Ender Rail Accelerator destroy terrain when used by players?")
            .define("griefing", true)

        ENDER_RAIL_ACCELERATOR_HIT_EXPLOSION_RADIUS = builder
            .comment("How large should the explosion be when the projectile hits a target? 4 is the same as a TNT block.")
            .defineDouble("hit_explosion_radius", 8)

        ENDER_RAIL_ACCELERATOR_SHOOT_EXPLOSION_RADIUS = builder
            .comment("How large should the explosion be when the projectile leaves the rifle")
            .defineDouble("shoot_explosion_radius", 4)

        ENDER_RAIL_ACCELERATOR_PROJECTILE_SPEED = builder
            .comment("How fast should Ender Rail Accelerator projectiles be?")
            .defineDouble("projectile_speed", 10)   //TODO: Check me

        ENDER_RAIL_ACCELERATOR_RECOIL = builder
            .comment("How much recoil should the Ender Rail Accelerator have?")
            .defineDouble("recoil", 30)

        ENDER_RAIL_ACCELERATOR_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage an Ender Rail Accelerator can deal?")
            .defineDouble("damage_minimum", 120)

        ENDER_RAIL_ACCELERATOR_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage an Ender Rail Accelerator can deal?")
            .defineDouble("damage_maximum", 150)

        builder.pop()
    }

    private fun enderRifle() {
        builder
            .comment("Ender Rifle settings")
            .push("ender_rifle")

        ENDER_RIFLE_DAMAGE_INCREASE_PER_TICK = builder
            .comment("How much should the damage increase per tick?")
            .defineDouble("damage_increase_per_tick", 1)

        ENDER_RIFLE_PROJECTILE_SPEED = builder
            .comment("How fast should Ender Rifle projectiles be?")
            .defineDouble("projectile_speed", 1.5)  //TODO: Check me

        ENDER_RIFLE_ZOOM_FACTOR = builder
            .comment("What should the zoom factor be for the Ender Rifle? Your FOV is multiplied by this value.")
            .defineDouble("zoom_factor", 0.3)

        ENDER_RIFLE_RECOIL = builder
            .comment("How much recoil should the Ender Rifle have?")
            .defineDouble("recoil", 3)

        ENDER_RIFLE_KNOCKBACK = builder
            .comment("How much knockback should the Ender Rifle have?")
            .defineDouble("knockback", 1)

        ENDER_RIFLE_COOLDOWN = builder
            .comment("How long should the Ender Rifle cooldown be?")
            .defineInteger("cooldown", 25)

        ENDER_RIFLE_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage an Ender Rifle can deal?")
            .defineDouble("damage_minimum", 4)

        ENDER_RIFLE_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage an Ender Rifle can deal?")
            .defineDouble("damage_maximum", 16)

        builder.pop()
    }

    private fun fenFire() {
        builder
            .comment("Fen Fire settings")
            .push("fen_fire")

        FEN_FIRE_PROJECTILE_SPEED = builder
            .comment("How fast should Fen Fire projectiles be?")
            .defineDouble("projectile_speed", 1.5)

        FEN_FIRE_LIGHT_LIFESPAN = builder
            .comment("How long should the light from Fen Fire last? -1 is forever.")
            .defineInteger("light_lifespan", -1, 0)

        FEN_FIRE_TARGET_BURN_TIME = builder
            .comment("How long should targets burn for?")
            .defineInteger("target_burn_time", 1 * 20)

        FEN_FIRE_TARGET_GLOW_TIME = builder
            .comment("How long should targets glow for?")
            .defineInteger("target_glow_time", 3 * 20)

        FEN_FIRE_COOLDOWN = builder
            .comment("How long should the Fen Fire cooldown be?")
            .defineInteger("cooldown", 20)

        builder.pop()
    }

    private fun fireworksRocketLauncher() {
        builder
            .comment("Fireworks Rocket Launcher settings")
            .push("fireworks_rocket_launcher")

        FIREWORKS_ROCKET_LAUNCHER_GRIEFING = builder
            .comment("Can the Fireworks Rocket Launcher destroy terrain when used by players?")
            .define("griefing", true)

        FIREWORKS_ROCKET_LAUNCHER_EXPLOSION_RADIUS = builder
            .comment("How large should Fireworks Rocket Launcher explosions be? 4 is the same as a TNT block.")
            .defineDouble("explosion_radius", 4)

        FIREWORKS_ROCKET_LAUNCHER_PROJECTILE_SPEED = builder
            .comment("How fast should Fireworks Rocket Launcher projectiles be?")
            .defineDouble("projectile_speed", 2)

        FIREWORKS_ROCKET_LAUNCHER_RECOIL = builder
            .comment("How much recoil should the Fireworks Rocket Launcher have?")
            .defineDouble("recoil", 3)

        FIREWORKS_ROCKET_LAUNCHER_COOLDOWN = builder
            .comment("How long should the Fireworks Rocket Launcher cooldown be?")
            .defineInteger("cooldown", 20)

        FIREWORKS_ROCKET_LAUNCHER_TIME_UNTIL_EXPLODE = builder
            .comment("How long can rockets fly before exploding??")
            .defineInteger("time_until_explode", 20)

        builder.pop()
    }

    private fun flintDuster() {
        builder
            .comment("Flint Duster settings")
            .push("flint_duster")

        FLINT_DUSTER_RANGE = builder
            .comment("How far should the Flint Duster reach?")
            .defineDouble("range", 7)

        FLINT_DUSTER_DAMAGE = builder
            .comment("How much damage should the Flint Duster deal?")
            .defineDouble("damage", 1)

        builder.pop()
    }

    private fun fourHeadedDragonBox() {
        builder
            .comment("Four Headed Dragon Box settings")
            .push("four_headed_dragon_box")

        FOUR_HEADED_DRAGON_BOX_GRIEFING = builder
            .comment("Can the Four Headed Dragon Box destroy terrain when used by players?")
            .define("griefing", true)

        FOUR_HEADED_DRAGON_BOX_EXPLOSION_RADIUS = builder
            .comment("How large should Four Headed Dragon Box explosions be? 4 is the same as a TNT block.")
            .defineDouble("explosion_radius", 1)

        FOUR_HEADED_DRAGON_BOX_PROJECTILE_SPEED = builder
            .comment("How fast should Four Headed Dragon Box projectiles be?")
            .defineDouble("projectile_speed", 1.3)

        FOUR_HEADED_DRAGON_BOX_RECOIL = builder
            .comment("How much recoil should the Four Headed Dragon Box have?")
            .defineDouble("recoil", 1)

        FOUR_HEADED_DRAGON_BOX_KNOCKBACK = builder
            .comment("How much knockback should the Four Headed Dragon Box have?")
            .defineDouble("knockback", 2)

        FOUR_HEADED_DRAGON_BOX_TARGET_BURN_TIME = builder
            .comment("How long should targets burn for?")
            .defineInteger("target_burn_time", 6 * 20)

        FOUR_HEADED_DRAGON_BOX_COOLDOWN = builder
            .comment("How long should the Four Headed Dragon Box cooldown be?")
            .defineInteger("cooldown", 10)

        FOUR_HEADED_DRAGON_BOX_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage a Four Headed Dragon Box can deal?")
            .defineDouble("damage_minimum", 4)

        FOUR_HEADED_DRAGON_BOX_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage a Four Headed Dragon Box can deal?")
            .defineDouble("damage_maximum", 6)

        builder.pop()
    }

    private fun frostLancer() {
        builder
            .comment("Frost Lancer settings")
            .push("frost_lancer")

        FROST_LANCER_ZOOM_FACTOR = builder
            .comment("What should the zoom factor be for the Frost Lancer? Your FOV is multiplied by this value.")
            .defineDouble("zoom_factor", 0.2)

        FROST_LANCER_PROJECTILE_SPEED = builder
            .comment("How fast should Frost Lancer projectiles be?")
            .defineDouble("projectile_speed", 3.5)

        FROST_LANCER_RECOIL = builder
            .comment("How much recoil should the Frost Lancer have?")
            .defineDouble("recoil", 4)

        FROST_LANCER_KNOCKBACK = builder
            .comment("How much knockback should the Frost Lancer have?")
            .defineDouble("knockback", 3)

        FROST_LANCER_COOLDOWN = builder
            .comment("How long should the Frost Lancer cooldown be?")
            .defineInteger("cooldown", 40)

        FROST_LANCER_NAUSEA_DURATION = builder
            .comment("How long should targets be nauseated for?")
            .defineInteger("nausea_duration", 6 * 20)

        FROST_LANCER_SLOWNESS_DURATION = builder
            .comment("How long should targets be slowed for?")
            .defineInteger("slowness_duration", 6 * 20)

        FROST_LANCER_SLOWNESS_STRENGTH = builder
            .comment("How strong should the slowness effect be?")
            .defineInteger("slowness_strength", 3)

        FROST_LANCER_FREEZING_DURATION = builder
            .comment("How long should targets be chilled for?")
            .defineInteger("freezing_duration", 6 * 20)

        FROST_LANCER_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage a Frost Lancer can deal?")
            .defineDouble("damage_minimum", 9)

        FROST_LANCER_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage a Frost Lancer can deal?")
            .defineDouble("damage_maximum", 18)

        builder.pop()
    }

}