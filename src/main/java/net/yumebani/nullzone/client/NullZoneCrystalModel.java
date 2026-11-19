package net.yumebani.nullzone.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.*;

import java.util.HashMap;
import java.util.Map;

public class NullZoneCrystalModel extends Model{

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "nullzone_crystal"), "main");

    public static final ResourceLocation DIAMOND_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/diamond_nullzone_crystal.png");
    public static final ResourceLocation EMERALD_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/emerald_nullzone_crystal.png");
    public static final ResourceLocation GOLD_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/gold_nullzone_crystal.png");
    public static final ResourceLocation AMETHYST_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/amethyst_nullzone_crystal.png");
    public static final ResourceLocation IRON_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/iron_nullzone_crystal.png");
    public static final ResourceLocation COPPER_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/copper_nullzone_crystal.png");
    public static final ResourceLocation COAL_OVERLAY = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/coal_nullzone_crystal.png");

    public static final Map<Boolean, Map<Item, ResourceLocation>> BASE_TEXTURES;
    public static final Map<Item, ResourceLocation> OVERLAY_TEXTURES;

    static {
        BASE_TEXTURES = new HashMap<>();
        BASE_TEXTURES.put(false, new HashMap<>());
        BASE_TEXTURES.put(true, new HashMap<>());

        OVERLAY_TEXTURES = new HashMap<>();

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.DIAMOND)).NULLZONE_CRYSTAL.get().asItem(), DIAMOND_OVERLAY);
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.EMERALD)).NULLZONE_CRYSTAL.get().asItem(), EMERALD_OVERLAY);
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.GOLD)).NULLZONE_CRYSTAL.get().asItem(), GOLD_OVERLAY);
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.AMETHYST)).NULLZONE_CRYSTAL.get().asItem(), AMETHYST_OVERLAY);
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.IRON)).NULLZONE_CRYSTAL.get().asItem(), IRON_OVERLAY);
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.COPPER)).NULLZONE_CRYSTAL.get().asItem(), COPPER_OVERLAY);
            OVERLAY_TEXTURES.put(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.COAL)).NULLZONE_CRYSTAL.get().asItem(), COAL_OVERLAY);

            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                if (series instanceof NullZoneBlockSeries target){
                    BASE_TEXTURES.get(false).put(target.NULLZONE_CRYSTAL.get().asItem(), ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/" + content.getMaterialPrefix() + "nullzone_crystal.png"));
                    BASE_TEXTURES.get(true).put(target.NULLZONE_CRYSTAL.get().asItem(), ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "textures/block/" + content.getMaterialPrefix() + "nullzone_crystal_down.png"));

                }
            }
        }
    }

    private final ModelPart crystal;
    private final ModelPart base;
    private final ModelPart root;

    public NullZoneCrystalModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
        this.crystal = root.getChild("crystal");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition crystal = partdefinition.addOrReplaceChild("crystal", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -9.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 15.0F, -8.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        crystal.render(poseStack, vertexConsumer, i, i1, i2);
        base.render(poseStack, vertexConsumer, i, i1, i2);
    }

    public void setupAnim(float time){
        this.crystal.resetPose();

        this.crystal.xScale = 0.6f;
        this.crystal.yScale = 0.6f;
        this.crystal.zScale = 0.6f;

        float rotationSpeed = 0.01F;
        this.crystal.yRot = time * rotationSpeed;

        float bobSpeed = 0.025F;
        float bobHeight = 1.0F;

        this.crystal.y = (float) (Math.sin(time * bobSpeed) * bobHeight);
    }
}
