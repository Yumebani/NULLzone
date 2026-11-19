package net.yumebani.nullzone.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.ModBlocks;
import net.yumebani.nullzone.blocks.NullZoneBlockSeries;
import net.yumebani.nullzone.blocks.NullZoneBlockSeriesBase;
import net.yumebani.nullzone.blocks.NullZoneMaterialBlockSeries;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NullZoneMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                if (series instanceof NullZoneBlockSeries target){
                    crystalItem(target.NULLZONE_CRYSTAL.get().asItem(), series.getMaterialPrefix() + series.getPrefix());
                }
            }
        }
    }

    private ItemModelBuilder crystalItem (Item item, String additive){
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        return withExistingParent(itemId.getPath(),
                ResourceLocation.fromNamespaceAndPath("nullzone", "block/null_crystal_item")).texture("fulltex",
                ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID,"block/" + additive + "nullzone_crystal_full"));
    }
}
