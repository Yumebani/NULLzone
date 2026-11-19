package net.yumebani.nullzone.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.items.ModItems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BlocksRegister =
            DeferredRegister.create(Registries.BLOCK, NullZoneMod.MODID);

    public static Map<NullZoneBlockMaterials, NullZoneMaterialBlockSeries> CONTENTS;

    static {
        CONTENTS = new HashMap<>();

        for (NullZoneBlockMaterials type : NullZoneBlockMaterials.values()){
            CONTENTS.put(type, new NullZoneMaterialBlockSeries(){
                @Override
                public NullZoneBlockMaterials getMaterialEnum() {
                    return type;
                }

                @Override
                public String getMaterialPrefix() {
                    return type.toString();
                }

                @Override
                public String getMaterialTitle() {
                    return type.toTitle();
                }

                @Override
                public Block getMaterial() {
                    return type.toMaterial();
                }

                @Override
                public Block getMaterialSlab() {
                    return type.toMaterialSlab();
                }
            });
        }
    }

    public static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Supplier<T> block){
        DeferredHolder<Block, T> toRet = BlocksRegister.register(name, block);
        registerBlockItem(name, toRet);
        return toRet;
    }

    private static <T extends Block> DeferredHolder<Item, Item> registerBlockItem(String name, DeferredHolder<Block, T> block) {
        return ModItems.ItemsRegister.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BlocksRegister.register(eventBus);
    }
}
