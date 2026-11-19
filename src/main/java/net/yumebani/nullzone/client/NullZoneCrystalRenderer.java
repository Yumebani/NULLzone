package net.yumebani.nullzone.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.yumebani.nullzone.blocks.custom.ModBlockProperties;
import net.yumebani.nullzone.blocks.custom.entity.NullZoneCrystalEntity;

public class NullZoneCrystalRenderer implements BlockEntityRenderer<NullZoneCrystalEntity> {
    private final NullZoneCrystalModel model;

    public NullZoneCrystalRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new NullZoneCrystalModel(context.bakeLayer(NullZoneCrystalModel.LAYER_LOCATION));
    }

    @Override
    public AABB getRenderBoundingBox(NullZoneCrystalEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(
                pos.offset(-1, -1, -1).getCenter(),
                pos.offset(2, 2, 2).getCenter()
        );
    }

    @Override
    public void render(NullZoneCrystalEntity nullZoneCrystalBlock, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        float time = 0.0f;
        if (nullZoneCrystalBlock.getLevel() != null) {
            time = nullZoneCrystalBlock.getLevel().getGameTime() + partialTick;
        }

        this.model.setupAnim(time);

        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(NullZoneCrystalModel.BASE_TEXTURES.get(nullZoneCrystalBlock.getBlockState().getValue(ModBlockProperties.DOWN)).get(nullZoneCrystalBlock.getBlockState().getBlock().asItem())));
        this.model.renderToBuffer(poseStack, baseConsumer, i, i1, 0xFFFFFFFF);

        VertexConsumer emissiveConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(NullZoneCrystalModel.OVERLAY_TEXTURES.get(nullZoneCrystalBlock.getBlockState().getBlock().asItem())));
        this.model.renderToBuffer(poseStack, emissiveConsumer, 15728880, i1, 0xFFFFFFFF);

        poseStack.popPose();
    }
}
