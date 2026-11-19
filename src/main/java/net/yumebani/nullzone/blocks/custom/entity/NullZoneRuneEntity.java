package net.yumebani.nullzone.blocks.custom.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class NullZoneRuneEntity extends BlockEntity {

    private boolean hasInit = false;

    private BlockPos crystalPos;
    private int id;

    public NullZoneRuneEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.NULLZONE_RUNE_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!this.hasInit && level != null) {
            this.hasInit = true;
            this.updateCrystal(pos);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putBoolean("has_crystal", crystalPos != null);
        if (crystalPos != null){
            tag.put("crystal_pos", NbtUtils.writeBlockPos(crystalPos));
            tag.putInt("crystal_id", this.id);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.getBoolean("has_crystal")){
            crystalPos = NbtUtils.readBlockPos(tag, "crystal_pos").get();
            id = tag.getInt("crystal_id");
        }
    }

    public void clearCrystal(){
        this.crystalPos = null;
    }

    public void updateCrystal(BlockPos pos){
        if (!getLevel().isClientSide()){
            if (crystalPos == null || (crystalPos != null && (!(this.getLevel().getBlockEntity(crystalPos) instanceof NullZoneCrystalEntity) || ((NullZoneCrystalEntity) this.getLevel().getBlockEntity(crystalPos)).getId() != this.id))){
                List<BlockPos> validCrystals = new ArrayList<>();
                for (Direction dir : Direction.values()){
                    if (this.getLevel().getBlockEntity(pos.relative(dir)) instanceof NullZoneRuneEntity rune){
                        BlockPos crystalPos = rune.getCrystalPos();
                        if (crystalPos != null){
                            if (this.getLevel().getBlockEntity(crystalPos) instanceof NullZoneCrystalEntity crystal){
                                if (crystal.isRuneValidRange(pos) && !validCrystals.contains(crystalPos)) validCrystals.add(crystalPos);
                                if (validCrystals.size() > 1){
                                    this.getLevel().destroyBlock(pos, true);
                                    return;
                                }
                            }
                        }
                    } else if (this.getLevel().getBlockEntity(pos.relative(dir)) instanceof NullZoneCrystalEntity crystal){
                        if (crystal.isRuneValidRange(pos) && !validCrystals.contains(pos.relative(dir))) validCrystals.add(pos.relative(dir));
                        if (validCrystals.size() > 1){
                            this.getLevel().destroyBlock(pos, true);
                            return;
                        }
                    }
                }
                if (!validCrystals.isEmpty()) {
                    crystalPos = validCrystals.getFirst();
                    if (this.getLevel().getBlockEntity(crystalPos) instanceof NullZoneCrystalEntity crystal){
                        this.id = crystal.handlePartAdded(this.worldPosition);
                    }
                }
            }
        }
    }

    public void onPartBroken() {
        if (this.crystalPos != null && level != null) {
            BlockEntity entity = level.getBlockEntity(this.crystalPos);
            if (entity instanceof NullZoneCrystalEntity crystal) {
                crystal.handlePartRemoved(this.worldPosition);
            }
        }
    }

    public BlockPos getCrystalPos() {
        return crystalPos;
    }
}
