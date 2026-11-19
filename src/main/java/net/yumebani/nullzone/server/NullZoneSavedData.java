package net.yumebani.nullzone.server;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mth;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NullZoneSavedData extends SavedData {

    private int zoneId = 0;
    private Map<String, Map<Integer, NullZoneRecord>> nullZones = new HashMap<>();

    public void registerNullZone(Level level, int id, NullZoneRecord record){
        if (!this.nullZones.containsKey(level.getDescriptionKey())){
            this.nullZones.put(level.getDescriptionKey(), new HashMap<>());
        }
        this.nullZones.get(level.getDescriptionKey()).put(id, record);
    }

    public void removeNullZone(Level level, int id){
        this.nullZones.get(level.getDescriptionKey()).remove(id);
    }

    public boolean isInNullZone(Level level, BlockPos pos){
        if (nullZones.containsKey(level.getDescriptionKey())){
            for (NullZoneRecord zone : nullZones.get(level.getDescriptionKey()).values()){
                if (Mth.abs(pos.getX() - zone.anchor().getX()) < zone.distX()){
                    if (Mth.abs(pos.getZ() - zone.anchor().getZ()) < zone.distZ()){
                        if (Mth.abs(pos.getY() - zone.anchor().getY()) < zone.distY()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static NullZoneSavedData create(){
        return new NullZoneSavedData();
    }

    public NullZoneSavedData loadMap(Map<String, Map<Integer, NullZoneRecord>> map){
        this.nullZones = map;
        return this;
    }

    public NullZoneSavedData loadId(int id){
        this.zoneId = id;
        return this;
    }

    public static NullZoneSavedData load(CompoundTag tag, HolderLookup.Provider registries){
        Map<String, Map<Integer, NullZoneRecord>> nullZones = new HashMap<>();
        int levelsCount = tag.getInt("levels");
        for (int i = 0; i < levelsCount; i++){
            String levelKey = tag.getString("level_" + i);
            nullZones.put(levelKey, new HashMap<>());
            int zoneCount = tag.getInt("zone_count_" + i);
            for (int j = 0; j < zoneCount; j++){
                nullZones.get(levelKey).put(
                        tag.getInt("zone_" + i + "_" + j + "_id"),
                        new NullZoneRecord(NbtUtils.readBlockPos(tag, "zone_" + i + "_" + j + "_anchor").get(),
                                tag.getInt("zone_" + i + "_" + j + "_distx"),
                                tag.getInt("zone_" + i + "_" + j + "_distz"),
                                tag.getInt("zone_" + i + "_" + j + "_disty")));
            }
        }
        return new NullZoneSavedData().loadMap(nullZones).loadId(tag.getInt("zoneid"));
    }

    @Override
    public CompoundTag save(CompoundTag pCompoundTag, HolderLookup.Provider registries) {
        pCompoundTag.putInt("levels", nullZones.size());
        List<String> keys = nullZones.keySet().stream().toList();
        for (int i = 0; i < nullZones.size(); i++){
            pCompoundTag.putString("level_" + i, keys.get(i));
            Map<Integer, NullZoneRecord> levelZones = nullZones.get(keys.get(i));
            pCompoundTag.putInt("zone_count_" + i, levelZones.size());
            List<Integer> keys2 = levelZones.keySet().stream().toList();
            for (int j = 0; j < levelZones.size(); j++){
                pCompoundTag.putInt("zone_" + i + "_" + j + "_id", keys2.get(j));
                NullZoneRecord record = levelZones.get(keys2.get(j));
                pCompoundTag.put("zone_" + i + "_" + j + "_anchor", NbtUtils.writeBlockPos(record.anchor()));
                pCompoundTag.putInt("zone_" + i + "_" + j + "_distx", record.distX());
                pCompoundTag.putInt("zone_" + i + "_" + j + "_distz", record.distZ());
                pCompoundTag.putInt("zone_" + i + "_" + j + "_disty", record.distY());
            }
        }
        pCompoundTag.putInt("zoneid", zoneId);
        return pCompoundTag;
    }

    public static final SavedData.Factory<NullZoneSavedData> FACTORY = new SavedData.Factory<>(
            NullZoneSavedData::create,
            NullZoneSavedData::load,
            DataFixTypes.SAVED_DATA_MAP_DATA
    );

    public static NullZoneSavedData computeIfAbsent(MinecraftServer server){
        return server.overworld().getDataStorage().computeIfAbsent(FACTORY, "null_zones");
    }

    public int getZoneId() {
        return zoneId++;
    }
}
