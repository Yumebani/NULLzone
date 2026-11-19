package net.yumebani.nullzone.blocks.custom.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.AABB;
import net.yumebani.nullzone.blocks.custom.ModBlockProperties;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneFunctionalBlock;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneSlabFunctionalBlock;
import net.yumebani.nullzone.server.NullZoneRecord;
import net.yumebani.nullzone.server.NullZoneSavedData;

public class NullZoneCrystalEntity extends BlockEntity {

    private boolean hasInit = false;

    private int id;
    private int sizePosX = 0;
    private int sizeNegX = 0;
    private int sizePosZ = 0;
    private int sizeNegZ = 0;
    private int sizeY = 0;

    public final AnimationState animationState = new AnimationState();

    public NullZoneCrystalEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.NULLZONE_CRYSTAL_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(worldPosition) instanceof NullZoneCrystalEntity crystal && !this.hasInit && this.getLevel() != null) {

            for (Direction dir : Direction.values()){
                if (this.getLevel().getBlockEntity(worldPosition.relative(dir)) instanceof NullZoneRuneEntity rune){
                    BlockPos crystalPos = rune.getCrystalPos();
                    if (crystalPos != null){
                        if (this.getLevel().getBlockEntity(crystalPos) instanceof NullZoneCrystalEntity target && target.getId() != this.id){
                            this.getLevel().destroyBlock(worldPosition, true);
                            return;
                        }
                    }
                } else if (this.getLevel().getBlockEntity(worldPosition.relative(dir)) instanceof NullZoneCrystalEntity target && target.getId() != this.id){
                    this.getLevel().destroyBlock(worldPosition, true);
                    return;
                }
            }

            this.hasInit = true;
            this.id = NullZoneSavedData.computeIfAbsent(getLevel().getServer()).getZoneId();
            boolean xLimit = false;
            boolean xnLimit = false;
            boolean yLimit = false;
            boolean zLimit = false;
            boolean znLimit = false;
            for (int i = 1; i <= 7; i++){
                if (!xLimit && !handlePartAdded(false, this.worldPosition.offset(i, 0, 0))){
                    xLimit = true;
                }
                if (!xnLimit && !handlePartAdded(false, this.worldPosition.offset(-i, 0, 0))){
                    xnLimit = true;
                }
                if (!yLimit && !handlePartAdded(false, this.worldPosition.offset(0, -i, 0))){
                    yLimit = true;
                }
                if (!zLimit && !handlePartAdded(false, this.worldPosition.offset(0, 0, i))){
                    zLimit = true;
                }
                if (!znLimit && !handlePartAdded(false, this.worldPosition.offset(0, 0, -i))){
                    znLimit = true;
                }
            }
            processDirty();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("id", id);
        tag.putInt("size_posx", sizePosX);
        tag.putInt("size_negx", sizeNegX);
        tag.putInt("size_posz", sizePosZ);
        tag.putInt("size_negz", sizeNegZ);
        tag.putInt("size_y", sizeY);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        id = tag.getInt("id");
        sizePosX = tag.getInt("size_posx");
        sizeNegX = tag.getInt("size_negx");
        sizePosZ = tag.getInt("size_posz");
        sizeNegZ = tag.getInt("size_negz");
        sizeY = tag.getInt("size_y");
    }

    public void clientTick(Level level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof NullZoneCrystalEntity crystal){
            if (!crystal.animationState.isStarted()) {
                crystal.animationState.start((int)level.getGameTime());
            }
        }
    }

    public void removeBlock(){
        setStatesForArea(false, this.worldPosition.offset(-sizeNegX, -sizeY, -sizeNegZ), sizeNegX + sizePosX + 1, sizeY + 1, sizeNegZ + sizePosZ + 1);
        NullZoneSavedData.computeIfAbsent(this.getLevel().getServer()).removeNullZone(this.getLevel(), this.id);
    }

    public boolean isRuneValidRange(BlockPos pos) {
        return (Mth.abs(this.worldPosition.getX() - pos.getX()) < 8) &&
                (Mth.abs(this.worldPosition.getZ() - pos.getZ()) < 8) &&
                (this.worldPosition.getY() - pos.getY() < 8 && this.worldPosition.getY() - pos.getY() >= 0);
    }

    public void handlePartRemoved(BlockPos worldPosition) {
        boolean dirty = false;


        if (!getLevel().isClientSide()) {

            int sizeNegXDif = worldPosition.getX() < this.worldPosition.getX() ? sizeNegX - (this.worldPosition.getX() - worldPosition.getX()) : 0;
            int sizePosXDif = worldPosition.getX() > this.worldPosition.getX() ? sizePosX - (worldPosition.getX() - this.worldPosition.getX()) : 0;
            int sizeYDif = worldPosition.getY() > this.worldPosition.getY() ? sizeY - (this.worldPosition.getY() - worldPosition.getY()) : 0;
            int sizeNegZDif = worldPosition.getZ() < this.worldPosition.getZ() ? sizeNegZ - (this.worldPosition.getZ() - worldPosition.getZ()) : 0;
            int sizePosZDif = worldPosition.getZ() > this.worldPosition.getZ() ? sizePosZ - (worldPosition.getZ() - this.worldPosition.getZ()) : 0;

            if (sizeNegXDif >= 0 && sizePosXDif >= 0 && sizeYDif >= 0 && sizeNegZDif >= 0 && sizePosZDif >= 0){
                if (worldPosition.getX() < this.worldPosition.getX() && this.worldPosition.getX() - worldPosition.getX() <= sizeNegX) {
                    BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY, -sizeNegZ);
                    while (sizeNegX >= this.worldPosition.getX() - worldPosition.getX()) {
                        setStatesForArea(false, corner, 1, sizeY + 1, sizeNegZ + sizePosZ + 1);
                        sizeNegX--;
                        dirty = true;
                        corner = corner.offset(1, 0, 0);
                    }
                } else if (worldPosition.getX() > this.worldPosition.getX() && worldPosition.getX() - this.worldPosition.getX() <= sizePosX) {
                    BlockPos corner = this.worldPosition.offset(sizePosX, -sizeY, -sizeNegZ);
                    while (sizePosX >= worldPosition.getX() - this.worldPosition.getX()) {
                        setStatesForArea(false, corner, 1, sizeY + 1, sizeNegZ + sizePosZ + 1);
                        sizePosX--;
                        dirty = true;
                        corner = corner.offset(-1, 0, 0);
                    }
                } else if (worldPosition.getZ() < this.worldPosition.getZ() && this.worldPosition.getZ() - worldPosition.getZ() <= sizeNegZ) {
                    BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY, -sizeNegZ);
                    while (sizeNegZ >= this.worldPosition.getZ() - worldPosition.getZ()) {
                        setStatesForArea(false, corner, sizeNegX + sizePosX + 1, sizeY + 1, 1);
                        sizeNegZ--;
                        dirty = true;
                        corner = corner.offset(0, 0, 1);
                    }
                } else if (worldPosition.getZ() > this.worldPosition.getZ() && worldPosition.getZ() - this.worldPosition.getZ() <= sizePosZ) {
                    BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY, sizePosZ);
                    while (sizePosZ >= worldPosition.getZ() - this.worldPosition.getZ()) {
                        setStatesForArea(false, corner, sizeNegX + sizePosX + 1, sizeY + 1, 1);
                        sizePosZ--;
                        dirty = true;
                        corner = corner.offset(0, 0, -1);
                    }
                } else if (this.worldPosition.getY() - worldPosition.getY() <= sizeY) {
                    BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY, -sizeNegZ);
                    while (sizeY >= this.worldPosition.getY() - worldPosition.getY()) {
                        setStatesForArea(false, corner, sizeNegX + sizePosX + 1, 1, sizeNegZ + sizePosZ + 1);
                        sizeY--;
                        dirty = true;
                        corner = corner.offset(0, 1, 0);
                    }
                }
            }
        }

        if (dirty) processDirty();
    }

    public int handlePartAdded(BlockPos worldPosition){
        handlePartAdded(true, worldPosition);
        return this.id;
    }

    public boolean handlePartAdded(boolean processDirty, BlockPos worldPosition) {
        boolean dirty = false;

        if (!getLevel().isClientSide()){
            if (worldPosition.getX() < this.worldPosition.getX() && this.worldPosition.getX() - worldPosition.getX() > sizeNegX){
                BlockPos corner = this.worldPosition.offset(-sizeNegX - 1, -sizeY, -sizeNegZ);
                while(sizeNegX < 7 && checkStructureValidityAndSet(corner, 1, sizeY + 1, sizeNegZ + sizePosZ + 1)){
                    sizeNegX++;
                    dirty = true;
                    corner = corner.offset(-1, 0, 0);
                }
            }
            if (worldPosition.getX() > this.worldPosition.getX() && worldPosition.getX() - this.worldPosition.getX() > sizePosX){
                BlockPos corner = this.worldPosition.offset(sizePosX + 1, -sizeY, -sizeNegZ);
                while(sizePosX < 7 && checkStructureValidityAndSet(corner, 1, sizeY + 1, sizeNegZ + sizePosZ + 1)){
                    sizePosX++;
                    dirty = true;
                    corner = corner.offset(1, 0, 0);
                }
            }
            if (worldPosition.getZ() < this.worldPosition.getZ() && this.worldPosition.getZ() - worldPosition.getZ() > sizeNegZ){
                BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY, -sizeNegZ - 1);
                while(sizeNegZ < 7 && checkStructureValidityAndSet(corner, sizeNegX + sizePosX + 1, sizeY + 1, 1)){
                    sizeNegZ++;
                    dirty = true;
                    corner = corner.offset(0, 0, -1);
                }
            }
            if (worldPosition.getZ() > this.worldPosition.getZ() && worldPosition.getZ() - this.worldPosition.getZ() > sizePosZ){
                BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY, sizePosZ + 1);
                while(sizePosZ < 7 && checkStructureValidityAndSet(corner, sizeNegX + sizePosX + 1, sizeY + 1, 1)){
                    sizePosZ++;
                    dirty = true;
                    corner = corner.offset(0, 0, 1);
                }
            }
            if (this.worldPosition.getY() - worldPosition.getY() > sizeY){
                BlockPos corner = this.worldPosition.offset(-sizeNegX, -sizeY - 1, -sizeNegZ);
                while(sizeY < 7 && checkStructureValidityAndSet(corner, sizeNegX + sizePosX + 1, 1, sizeNegZ + sizePosZ + 1)){
                    sizeY++;
                    dirty = true;
                    corner = corner.offset(0, -1, 0);
                }
            }

            if (dirty && processDirty) processDirty();
        }

        return dirty;
    }

    private void processDirty(){
        if (!getLevel().isClientSide()){
            int xMiddle = (int)(((sizeNegX - sizePosX) / 2f) * 32);
            int zMiddle = (int)(((sizeNegZ - sizePosZ) / 2f) * 32);
            int xDist = (int)(((sizeNegX + sizePosX) / 2f) * 32) + 32;
            int zDist = (int)(((sizeNegZ + sizePosZ) / 2f) * 32) + 32;
            int yDist = (int)(((sizeY) / 2f) * 64) + 64;
            NullZoneSavedData.computeIfAbsent(this.getLevel().getServer()).registerNullZone(this.getLevel(), this.id,
                    new NullZoneRecord(this.worldPosition.offset(xMiddle, 0, zMiddle), xDist, yDist, zDist));
        }
    }

    private boolean checkStructureValidityAndSet(BlockPos corner, int x, int y, int z){
        boolean validity = checkStructureValidity(corner, x, y, z);
        setStatesForArea(validity, corner, x, y, z);
        return validity;
    }

    private boolean checkStructureValidity(BlockPos corner, int x, int y, int z){
        for (int xi = 0; xi < x; xi++){
            for (int zi = 0; zi < z; zi++){
                for (int yi = 0; yi < y; yi++){
                    BlockState state = this.level.getBlockState(corner.offset(xi, yi, zi));
                    if (corner.offset(xi, yi, zi) != this.worldPosition){
                        if (state.getBlock() instanceof NullZoneRuneSlabFunctionalBlock){
                            if (corner.getY() + yi + 1 == this.worldPosition.getY() && state.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM){
                                return false;
                            } else if (corner.getY() + yi + 1 < this.worldPosition.getY() && state.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.DOUBLE) {
                                return false;
                            }
                        } else if (!(state.getBlock() instanceof NullZoneRuneFunctionalBlock)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void setStatesForArea(boolean value, BlockPos corner, int x, int y, int z){
        for (int xi = 0; xi < x; xi++){
            for (int zi = 0; zi < z; zi++){
                for (int yi = 0; yi < y; yi++){
                    BlockState state = this.level.getBlockState(corner.offset(xi, yi, zi));
                    if ((state.getBlock() instanceof NullZoneRuneFunctionalBlock)){
                        this.level.setBlock(corner.offset(xi, yi, zi), state.setValue(ModBlockProperties.ACTIVE, value), 2);
                    }
                }
            }
        }
    }

    public int getId() {
        return this.id;
    }
}
