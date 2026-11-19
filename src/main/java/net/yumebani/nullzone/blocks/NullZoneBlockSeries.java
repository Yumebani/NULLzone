package net.yumebani.nullzone.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yumebani.nullzone.blocks.custom.NullZoneCrystalBlock;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneBlockDecorative;
import net.yumebani.nullzone.blocks.custom.NullZoneRuneSlabBlockDecorative;

public abstract class NullZoneBlockSeries extends NullZoneBlockSeriesBase{

    public final DeferredHolder<Block, Block> NULLZONE_CRYSTAL = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "null_crystal",
            () -> new NullZoneCrystalBlock(BlockBehaviour.Properties.ofFullCopy(getMaterialSlab()).pushReaction(PushReaction.BLOCK)));
    public final DeferredHolder<Block, Block> NULLZONE_DECORATIVE_RUNE_INACTIVE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_inactive",
            () -> new NullZoneRuneBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterial())));
    public final DeferredHolder<Block, Block> NULLZONE_DECORATIVE_RUNE_ACTIVE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_active",
            () -> new NullZoneRuneBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterial())));
    public final DeferredHolder<Block, Block> NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_slab_inactive",
            () -> new NullZoneRuneSlabBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterialSlab())));
    public final DeferredHolder<Block, Block> NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_slab_active",
            () -> new NullZoneRuneSlabBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterialSlab())));
    public final DeferredHolder<Block, Block> PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_rune_pillar_inactive",
            () -> new NullZoneRuneBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterial())));
    public final DeferredHolder<Block, Block> PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE = seriesRegisterBlock(getMaterialPrefix() + getPrefix() + "nullzone_decorative_pillar_rune_active",
            () -> new NullZoneRuneBlockDecorative(BlockBehaviour.Properties.ofFullCopy(getMaterial())));
}
