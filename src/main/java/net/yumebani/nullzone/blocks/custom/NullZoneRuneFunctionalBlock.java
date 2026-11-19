package net.yumebani.nullzone.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.yumebani.nullzone.blocks.custom.entity.ModBlockEntities;
import net.yumebani.nullzone.blocks.custom.entity.NullZoneRuneEntity;
import org.jetbrains.annotations.Nullable;

public class NullZoneRuneFunctionalBlock extends BaseEntityBlock {

    public static final MapCodec<NullZoneRuneFunctionalBlock> CODEC = simpleCodec(NullZoneRuneFunctionalBlock::new);

    public NullZoneRuneFunctionalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ModBlockProperties.ACTIVE, false).setValue(ModBlockProperties.UP, false).setValue(ModBlockProperties.DOWN, false));
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        ((NullZoneRuneEntity) level.getBlockEntity(pos)).updateCrystal(pos);

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
        builder.add(ModBlockProperties.ACTIVE, ModBlockProperties.UP, ModBlockProperties.DOWN);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NullZoneRuneEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (!level.isClientSide()) {
            return createTickerHelper(blockEntityType, ModBlockEntities.NULLZONE_RUNE_BLOCK_ENTITY.get(),
                    (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
        } else return null;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide()) {
                BlockEntity entity = level.getBlockEntity(pos);
                if (entity instanceof NullZoneRuneEntity rune) {
                    rune.onPartBroken();
                }
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
