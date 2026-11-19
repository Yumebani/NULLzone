package net.yumebani.nullzone.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.yumebani.nullzone.blocks.*;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BlocksRegister.getEntries().stream().map(entry -> {return ((Block) entry.get());})::iterator;
    }

    @Override
    protected void generate() {

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()) {

                this.dropSelf(series.NULLZONE_RUNE.get());
                this.dropSelf(series.NULLZONE_RUNE_PILLAR.get());
                this.add(series.NULLZONE_RUNE_SLAB.get(), block -> createSlabItemTable(series.NULLZONE_RUNE_SLAB.get()));

                if (series instanceof NullZoneBlockSeries target) {

                    this.dropSelf(target.NULLZONE_CRYSTAL.get());
                    this.dropSelf(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get());
                    this.dropSelf(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get());
                    this.add(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get(), block -> createSlabItemTable(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get()));
                    this.dropSelf(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get());
                    this.dropSelf(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get());
                    this.add(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get(), block -> createSlabItemTable(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get()));

                } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target){

                    this.dropSelf(target.NULLZONE_DECORATIVE_RUNE.get());
                    this.dropSelf(target.PILLAR_NULLZONE_DECORATIVE_RUNE.get());
                    this.add(target.NULLZONE_DECORATIVE_RUNE_SLAB.get(), block -> createSlabItemTable(target.NULLZONE_DECORATIVE_RUNE_SLAB.get()));

                }
            }
        }
    }
}
