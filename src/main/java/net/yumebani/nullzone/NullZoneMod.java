package net.yumebani.nullzone;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.yumebani.nullzone.blocks.ModBlocks;
import net.yumebani.nullzone.blocks.custom.entity.ModBlockEntities;
import net.yumebani.nullzone.blocks.items.ModItems;

@Mod(NullZoneMod.MODID)
public class NullZoneMod {

    public static final String MODID = "nullzone";

    public NullZoneMod(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        CreativeModeTabs.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
}
