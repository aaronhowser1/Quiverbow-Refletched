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