package dev.aaronhowser.mods.quiverbowrefletched.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

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

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val direction = state.getValue(FACING)
        return Block.canSupportCenter(level, pos.relative(direction), direction.opposite)
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        val facing = state.getValue(FACING)
        return shapes[facing] ?: shapes[Direction.DOWN]!!
    }

    override fun codec(): MapCodec<FenFireBlock> {
        return CODEC
    }

    companion object {
        private val CODEC = simpleCodec(::FenFireBlock)

        private val shapes: Map<Direction, VoxelShape> = mapOf(
            Direction.DOWN to box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0),
            Direction.UP to box(1.0, 15.0, 1.0, 15.0, 16.0, 15.0),
            Direction.NORTH to box(1.0, 1.0, 0.0, 15.0, 15.0, 1.0),
            Direction.SOUTH to box(1.0, 1.0, 15.0, 15.0, 15.0, 16.0),
            Direction.WEST to box(0.0, 1.0, 1.0, 1.0, 15.0, 15.0),
            Direction.EAST to box(15.0, 1.0, 1.0, 16.0, 15.0, 15.0),
        )

    }
}