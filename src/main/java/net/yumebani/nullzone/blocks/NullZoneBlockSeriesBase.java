package net.yumebani.nullzone.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneFunctionalBlock;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneSlabFunctionalBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class NullZoneBlockSeriesBase {

    public abstract String getPrefix();
    public abstract String getPrefixTitle();
    public abstract String getMaterialPrefix();
    public abstract Block getMaterial();
    public abstract Block getMaterialSlab();

    public final List<DeferredHolder<Block, Block>> CONTENTS = new ArrayList<>();

    public final DeferredHolder<Block, Block> NULLZONE_RUNE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_rune",
            () -> new NullZoneRuneFunctionalBlock(BlockBehaviour.Properties.ofFullCopy(getMaterial()).pushReaction(PushReaction.BLOCK)));
    public final DeferredHolder<Block, Block> NULLZONE_RUNE_SLAB = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_rune_slab",
            () -> new NullZoneRuneSlabFunctionalBlock(BlockBehaviour.Properties.ofFullCopy(getMaterialSlab()).pushReaction(PushReaction.BLOCK)));
    public final DeferredHolder<Block, Block> NULLZONE_RUNE_PILLAR = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_rune_pillar",
            () -> new NullZoneRuneFunctionalBlock(BlockBehaviour.Properties.ofFullCopy(getMaterial()).pushReaction(PushReaction.BLOCK)));

    protected DeferredHolder<Block, Block> seriesRegisterBlock(String name, Supplier<Block> block){
        DeferredHolder<Block, Block> item = ModBlocks.registerBlock(name, block);
        CONTENTS.add(item);
        return item;
    }
}
