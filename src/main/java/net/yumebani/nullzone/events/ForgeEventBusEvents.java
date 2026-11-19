package net.yumebani.nullzone.events;

import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.server.NullZoneSavedData;

@EventBusSubscriber(modid = NullZoneMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEventBusEvents {

    @SubscribeEvent
    public static void positionCheck(MobSpawnEvent.PositionCheck event) {
        if (event.getEntity().getType().getCategory() == MobCategory.MONSTER && NullZoneSavedData.computeIfAbsent(event.getLevel().getServer()).isInNullZone(event.getLevel().getLevel(), event.getEntity().blockPosition())){
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }
    }
}
