package dev.aaronhowser.mods.quiverbowrefletched.config

import net.neoforged.neoforge.common.ModConfigSpec
import org.apache.commons.lang3.tuple.Pair

class ServerConfig(
    private val builder: ModConfigSpec.Builder
) {

    companion object {
        private val configPair: Pair<ServerConfig, ModConfigSpec> = ModConfigSpec.Builder().configure(::ServerConfig)

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
        lateinit var AUTO_CROSSBOW_RECOIL: ModConfigSpec.DoubleValue
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
        lateinit var DOUBLE_CROSSBOW_COOLDOWN: ModConfigSpec.DoubleValue
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
        lateinit var ENDER_RAIL_ACCELERATOR_KNOCKBACK: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_RECOIL: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_COOLDOWN: ModConfigSpec.IntValue
        lateinit var ENDER_RAIL_ACCELERATOR_DAMAGE_MINIMUM: ModConfigSpec.DoubleValue
        lateinit var ENDER_RAIL_ACCELERATOR_DAMAGE_MAXIMUM: ModConfigSpec.DoubleValue

        lateinit var ENDER_RIFLE_DAMAGE_INCREASE_PER_TICK: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_PROJECTILE_SPEED: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_ZOOM_FACTOR: ModConfigSpec.DoubleValue
        lateinit var ENDER_RIFLE_RECOIL: ModConfigSpec.DoubleValue
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

    }

    private fun aquaAccelerator() {
        builder
            .comment("Aqua Accelerator settings")
            .push("aqua_accelerator")

        AQUA_ACCELERATOR_PROJECTILE_SPEED = builder
            .comment("How fast should Aqua Accelerator projectiles travel?")
            .defineInRange("projectile_speed", 1.5, 0.0, Double.MAX_VALUE)

        builder.pop()
    }

    private fun arrowMortar() {
        builder
            .comment("Arrow Mortar settings")
            .push("arrow_mortar")

        ARROW_MORTAR_PROJECTILE_SPEED = builder
            .comment("How fast should Arrow Mortar projectiles travel?")
            .defineInRange("projectile_speed", 1.5, 0.0, Double.MAX_VALUE)

        ARROW_MORTAR_RECOIL = builder
            .comment("How much recoil should Arrow Mortar have?")
            .defineInRange("recoil", 1.0, 3.0, Double.MAX_VALUE)

        ARROW_MORTAR_COOLDOWN = builder
            .comment("How long should the Arrow Mortar cooldown be?")
            .defineInRange("cooldown", 20, 0, Integer.MAX_VALUE)

        ARROW_MORTAR_DAMAGE_MINIMUM = builder
            .comment("What is the minimum damage Arrow Mortar can deal?")
            .defineInRange("damage_minimum", 2.0, 0.0, Double.MAX_VALUE)

        ARROW_MORTAR_DAMAGE_MAXIMUM = builder
            .comment("What is the maximum damage Arrow Mortar can deal?")
            .defineInRange("damage_maximum", 10.0, 0.0, Double.MAX_VALUE)

        builder.pop()
    }

}