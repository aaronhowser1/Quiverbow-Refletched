package dev.aaronhowser.mods.quiverbowrefletched

import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(QuiverBowRefletched.ID)
object QuiverBowRefletched {
    const val ID = "quiverbow-refletched"

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")
    }
}