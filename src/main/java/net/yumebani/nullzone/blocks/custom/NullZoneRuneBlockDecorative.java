package net.yumebani.nullzone.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.Nullable;

public class NullZoneRuneBlockDecorative extends Block {

    public NullZoneRuneBlockDecorative(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ModBlockProperties.UP, false).setValue(ModBlockProperties.DOWN, false));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && (!neighborState.hasProperty(BlockStateProperties.SLAB_TYPE) || neighborState.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.TOP)){
            return state.setValue(ModBlockProperties.UP, neighborState.hasProperty(ModBlockProperties.DOWN));
        } else if (direction == Direction.DOWN && (!neighborState.hasProperty(BlockStateProperties.SLAB_TYPE) || neighborState.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.BOTTOM)){
            return state.setValue(ModBlockProperties.DOWN, neighborState.hasProperty(ModBlockProperties.UP));
        } else return state;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        return this.defaultBlockState()
                .setValue(ModBlockProperties.UP, level.getBlockState(pos.above()).hasProperty(ModBlockProperties.DOWN) && (!state.hasProperty(BlockStateProperties.SLAB_TYPE) || state.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.TOP))
                .setValue(ModBlockProperties.DOWN, level.getBlockState(pos.below()).hasProperty(ModBlockProperties.UP) && (!state.hasProperty(BlockStateProperties.SLAB_TYPE) || state.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.BOTTOM));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ModBlockProperties.UP, ModBlockProperties.DOWN);
    }
}
