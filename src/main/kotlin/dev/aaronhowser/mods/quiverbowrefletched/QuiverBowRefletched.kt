package dev.aaronhowser.mods.quiverbowrefletched

import dev.aaronhowser.mods.quiverbowrefletched.config.ServerConfig
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModRegistries
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.client.gui.ConfigurationScreen
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runWhenOn

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

        runWhenOn(Dist.CLIENT) {
            val screenFactory = IConfigScreenFactory { container, screen -> ConfigurationScreen(container, screen) }
            modContainer.registerExtensionPoint(IConfigScreenFactory::class.java, screenFactory)
        }

        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.CONFIG_SPEC)
    }

    //TODO: Check old quiverbow config for features it has that we don't (like projectile speeds, knockback, cooldown, etc

}