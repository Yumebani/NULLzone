package net.yumebani.nullzone.blocks.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yumebani.nullzone.NullZoneMod;

public class ModItems {

    public static final DeferredRegister<Item> ItemsRegister =
            DeferredRegister.create(Registries.ITEM, NullZoneMod.MODID);

    public static void register(IEventBus eventBus){
        ItemsRegister.register(eventBus);
    }
}
