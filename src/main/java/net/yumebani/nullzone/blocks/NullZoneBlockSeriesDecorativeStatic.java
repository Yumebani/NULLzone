package net.yumebani.nullzone.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneBlockDecorative;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneSlabBlockDecorative;

public abstract class NullZoneBlockSeriesDecorativeStatic extends NullZoneBlockSeriesBase{

    public final DeferredHolder<Block, Block> NULLZONE_DECORATIVE_RUNE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune",
            () -> new NullZoneRuneBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterial())));
    public final DeferredHolder<Block, Block> NULLZONE_DECORATIVE_RUNE_SLAB = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_slab",
            () -> new NullZoneRuneSlabBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterialSlab())));
    public final DeferredHolder<Block, Block> PILLAR_NULLZONE_DECORATIVE_RUNE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_pillar",
            () -> new NullZoneRuneBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterial())));
}
