package dev.aaronhowser.mods.quiverbowrefletched.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.PushReaction

class FenFireBlock(
    properties: Properties = Blocks.GLOWSTONE
        .properties()
        .replaceable()
        .noLootTable()
        .instabreak()
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .strength(0f, 0f)
) : DirectionalBlock(properties) {

    init {
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.DOWN))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
        return defaultBlockState().setValue(FACING, context.clickedFace.opposite)
    }

    override fun codec(): MapCodec<FenFireBlock> {
        return CODEC
    }

    companion object {
        private val CODEC = simpleCodec(::FenFireBlock)
    }
}