package dev.aaronhowser.mods.quiverbowrefletched.registry

import dev.aaronhowser.mods.quiverbowrefletched.QuiverBowRefletched
import dev.aaronhowser.mods.quiverbowrefletched.block.FenFireBlock
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister

object ModBlocks {

    val BLOCK_REGISTRY: DeferredRegister.Blocks =
        DeferredRegister.createBlocks(QuiverBowRefletched.ID)

    val FEN_FIRE_BLOCK: DeferredBlock<FenFireBlock> =
        registerBlock("fen_fire_block") { FenFireBlock() }

    private fun <T : Block> registerBlock(
        name: String,
        supplier: () -> T
    ): DeferredBlock<T> {
        val block = BLOCK_REGISTRY.register(name, supplier)

        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(name, block)

        return block
    }

}