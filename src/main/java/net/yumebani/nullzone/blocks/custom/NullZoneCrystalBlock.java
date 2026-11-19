package net.yumebani.nullzone.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yumebani.nullzone.blocks.custom.entity.ModBlockEntities;
import net.yumebani.nullzone.blocks.custom.entity.NullZoneCrystalEntity;
import net.yumebani.nullzone.blocks.custom.entity.NullZoneRuneEntity;
import org.jetbrains.annotations.Nullable;

public class NullZoneCrystalBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final MapCodec<NullZoneCrystalBlock> CODEC = simpleCodec(NullZoneCrystalBlock::new);
    public static final VoxelShape AABB = Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)8.0F, (double)16.0F);


    public NullZoneCrystalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false).setValue(ModBlockProperties.DOWN, false));
    }

    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.WATERLOGGED).add(ModBlockProperties.DOWN);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        switch (pathComputationType) {
            case LAND -> {
                return false;
            }
            case WATER -> {
                return state.getFluidState().is(FluidTags.WATER);
            }
            case AIR -> {
                return false;
            }
            default -> {
                return false;
            }
        }
    }


    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN){
            return state.setValue(ModBlockProperties.DOWN, neighborState.hasProperty(ModBlockProperties.UP));
        } else return state;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return this.defaultBlockState()
                .setValue(ModBlockProperties.DOWN, level.getBlockState(pos.below()).hasProperty(ModBlockProperties.UP));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NullZoneCrystalEntity(blockPos, blockState);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 8;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide()) {
                BlockEntity entity = level.getBlockEntity(pos);
                if (entity instanceof NullZoneCrystalEntity crystal) {
                    crystal.removeBlock();
                }
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()){
            return createTickerHelper(blockEntityType, ModBlockEntities.NULLZONE_CRYSTAL_BLOCK_ENTITY.get(),
                    (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.clientTick(pLevel1, pPos, pState1));
        } else {
            return createTickerHelper(blockEntityType, ModBlockEntities.NULLZONE_CRYSTAL_BLOCK_ENTITY.get(),
                    (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
        }
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }
}
