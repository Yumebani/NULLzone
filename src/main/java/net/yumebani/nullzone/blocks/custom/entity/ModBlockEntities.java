package net.yumebani.nullzone.blocks.custom.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.*;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> blockEntityRegister =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NullZoneMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NullZoneCrystalEntity>> NULLZONE_CRYSTAL_BLOCK_ENTITY =
            blockEntityRegister.register("nullzone_crystal_block_entity",
                    () -> {
                        List<Block> blocks = new ArrayList<>();

                        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
                            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                                if (series instanceof NullZoneBlockSeries target){
                                    blocks.add(target.NULLZONE_CRYSTAL.get());
                                }
                            }
                        }

                        return BlockEntityType.Builder.of(NullZoneCrystalEntity::new, blocks.toArray(new Block[]{})).build(null);
                    });

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NullZoneRuneEntity>> NULLZONE_RUNE_BLOCK_ENTITY =
            blockEntityRegister.register("nullzone_rune_block_entity",
                    () -> {
                        List<Block> blocks = new ArrayList<>();

                        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
                            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                                blocks.add(series.NULLZONE_RUNE.get());
                                blocks.add(series.NULLZONE_RUNE_SLAB.get());
                                blocks.add(series.NULLZONE_RUNE_PILLAR.get());
                            }
                        }

                        return BlockEntityType.Builder.of(NullZoneRuneEntity::new, blocks.toArray(new Block[]{})).build(null);
                    });

    public static void register(IEventBus eventBus){

        blockEntityRegister.register(eventBus);
    }
}
