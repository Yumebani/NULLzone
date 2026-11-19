package net.yumebani.nullzone.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.custom.entity.ModBlockEntities;
import net.yumebani.nullzone.client.NullZoneCrystalModel;
import net.yumebani.nullzone.client.NullZoneCrystalRenderer;

@EventBusSubscriber(modid = NullZoneMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ForgeEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NullZoneCrystalModel.LAYER_LOCATION, NullZoneCrystalModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.NULLZONE_CRYSTAL_BLOCK_ENTITY.get(), NullZoneCrystalRenderer::new);
    }
}
