package dev.aaronhowser.mods.quiverbowrefletched.datagen.model

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModBlocks
import net.minecraft.core.Direction
import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.DirectionalBlock
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.ConfiguredModel
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(
    output: PackOutput,
    private val existingFileHelper: ExistingFileHelper
) : BlockStateProvider(output, QuiverBowRefletched.ID, existingFileHelper) {

    override fun registerStatesAndModels() {
        fenFire()
    }

    private fun fenFire() {
        val block = ModBlocks.FEN_FIRE_BLOCK.get()

        val texture = mcLoc("block/glowstone")

        getVariantBuilder(block)
            .forAllStates {
                val facing = it.getValue(DirectionalBlock.FACING)

                val yRotation = when (facing) {
                    Direction.SOUTH -> 0
                    Direction.WEST -> 90
                    Direction.EAST -> 270
                    Direction.NORTH -> 180
                    else -> 0
                }

                val xRotation = when (facing) {
                    Direction.UP -> 180
                    Direction.DOWN -> 0
                    else -> 90
                }

                val model = models()
                    .withExistingParent("fen_fire_${facing.name.lowercase()}", mcLoc("block/thin_block"))
                    .texture("texture", texture)
                    .texture("particle", texture)

                    .element()
                    .from(1f, 0f, 1f)
                    .to(15f, 1f, 15f)
                    .textureAll("#texture")
                    .end()

                var configuredModel = ConfiguredModel
                    .builder()
                    .modelFile(model)

                if (yRotation != 0) {
                    configuredModel = configuredModel.rotationY(yRotation)
                }
                if (xRotation != 0) {
                    configuredModel = configuredModel.rotationX(xRotation)
                }

                configuredModel.build()
            }

        simpleBlockItem(
            block,
            ItemModelBuilder(
                modLoc("block/fen_fire_up"),
                existingFileHelper
            )
        )
    }

}