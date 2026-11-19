package net.yumebani.nullzone.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.*;
import net.yumebani.nullzone.tag.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, NullZoneMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                this.tag(ModTags.Items.RUNE_RUNE_BLOCKS)
                        .add(series.NULLZONE_RUNE.get().asItem());
            }
        }

        NullZoneBlockMaterials[] brickMats = new NullZoneBlockMaterials[]{
                NullZoneBlockMaterials.STONE_BRICKS,
                NullZoneBlockMaterials.QUARTZ_BRICKS,
                NullZoneBlockMaterials.NETHER_BRICKS,
                NullZoneBlockMaterials.MUD_BRICKS,
                NullZoneBlockMaterials.BLACKSTONE_BRICKS
        };

        for (NullZoneBlockMaterials brickMat : brickMats){
            NullZoneMaterialBlockSeries content = ModBlocks.CONTENTS.get(brickMat);
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                for (DeferredHolder<Block, Block> blockBlockDeferredHolder : series.CONTENTS) {
                    this.tag(ModTags.Items.BRICK_RUNE_BLOCKS)
                            .add(blockBlockDeferredHolder.get().asItem());
                }
            }
        }
    }
}
