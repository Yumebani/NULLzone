package net.yumebani.nullzone;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yumebani.nullzone.blocks.*;

public class CreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NullZoneMod.MODID);

    public static void register(IEventBus eventBus){
        TAB_REGISTER.register(eventBus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NULLZONE_DECOR_TAB = TAB_REGISTER.register("nullzone_decor_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(((NullZoneBlockSeries) ModBlocks.CONTENTS.get(NullZoneBlockMaterials.STONE).CONTENTS.get(NullZoneBlockTypes.DIAMOND)).PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get()))
                    .title(Component.translatable("creativetab.nullzone.decor"))
                    .displayItems(CreativeModeTabs::registerDecorTabItems)
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NULLZONE_FUNCTIONAL_TAB = TAB_REGISTER.register("nullzone_functional_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(((NullZoneBlockSeries) ModBlocks.CONTENTS.get(NullZoneBlockMaterials.STONE).CONTENTS.get(NullZoneBlockTypes.DIAMOND)).NULLZONE_CRYSTAL.get()))
                    .title(Component.translatable("creativetab.nullzone.functional"))
                    .displayItems(CreativeModeTabs::registerFunctionalTabItems)
                    .build());

    public static final void registerDecorTabItems(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output){

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){

                if (series instanceof NullZoneBlockSeries target){

                    output.accept(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get());
                    output.accept(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get());
                    output.accept(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get());
                    output.accept(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get());
                    output.accept(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get());
                    output.accept(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get());

                } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target){

                    output.accept(target.NULLZONE_DECORATIVE_RUNE.get());
                    output.accept(target.PILLAR_NULLZONE_DECORATIVE_RUNE.get());
                    output.accept(target.NULLZONE_DECORATIVE_RUNE_SLAB.get());
                }
            }
        }
    }

    public static final void registerFunctionalTabItems(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output){

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){

                output.accept(series.NULLZONE_RUNE.get());
                output.accept(series.NULLZONE_RUNE_PILLAR.get());
                output.accept(series.NULLZONE_RUNE_SLAB.get());

                if (series instanceof NullZoneBlockSeries target){

                    output.accept(target.NULLZONE_CRYSTAL.get());

                }
            }
        }
    }
}
