package dev.aaronhowser.mods.quiverbowrefletched

import dev.aaronhowser.mods.quiverbowrefletched.registry.ModRegistries
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(QuiverBowRefletched.ID)
class QuiverBowRefletched(
    modContainer: ModContainer
) {
    companion object {
        const val ID = "quiverbow_refletched"
        val LOGGER: Logger = LogManager.getLogger(ID)
    }

    init {
        ModRegistries.register(MOD_BUS)

        LOGGER.log(Level.INFO, "Hello world!")
    }
}