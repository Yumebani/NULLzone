package net.yumebani.nullzone.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.*;
import net.yumebani.nullzone.blocks.custom.ModBlockProperties;
import net.yumebani.nullzone.tag.ModTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NullZoneMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        crystals();
        varWeightRuneBlocks(List.of(1, 1, 1, 1));
        runePillarBlocks();
        runeSlabs();
    }

    protected void crystals() {

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){
                if (series instanceof NullZoneBlockSeries target){
                    crystalBlock(target.NULLZONE_CRYSTAL, series.getMaterialPrefix() + series.getPrefix());
                }
            }
        }
    }

    private void crystalBlock (DeferredHolder<Block, Block> blockRegistryObject, String additive){

        ResourceLocation fullText = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID,"block/" + additive + "nullzone_crystal_full");

        getVariantBuilder(blockRegistryObject.get()).forAllStates((blockState -> {
            return ConfiguredModel.builder().modelFile(models().withExistingParent(blockRegistryObject.getId().getPath(), modLoc("block/null_crystal_item"))
                    .texture("fulltex", fullText)
                    .texture("particle", fullText)).build();
        }));
    }

    private void runeSlabs(){

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){
            ResourceLocation topbotModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_topbot");
            ResourceLocation sideModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_slab");
            ResourceLocation sideUpModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_slab_up");
            ResourceLocation sideDownModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_slab_down");
            ResourceLocation sideUpDownModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_slab_up_down");

            BlockModelBuilder baseModelBottom = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_base_bottom", sideModelTex, topbotModelTex, SlabType.BOTTOM);
            BlockModelBuilder upModelBottom = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_up_bottom", sideUpModelTex, topbotModelTex, SlabType.BOTTOM);
            BlockModelBuilder downModelBottom = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_down_bottom", sideDownModelTex, topbotModelTex, SlabType.BOTTOM);
            BlockModelBuilder upDownModelBottom = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_up_down_bottom", sideUpDownModelTex, topbotModelTex, SlabType.BOTTOM);

            BlockModelBuilder baseModelTop = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_base_top", sideModelTex, topbotModelTex, SlabType.TOP);
            BlockModelBuilder upModelTop = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_up_top", sideUpModelTex, topbotModelTex, SlabType.TOP);
            BlockModelBuilder downModelTop = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_down_top", sideDownModelTex, topbotModelTex, SlabType.TOP);
            BlockModelBuilder upDownModelTop = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_up_down_top", sideUpDownModelTex, topbotModelTex, SlabType.TOP);

            BlockModelBuilder baseModelDouble = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_base_double", sideModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder upModelDouble = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_up_double", sideUpModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder downModelDouble = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_down_double", sideDownModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder upDownModelDouble = getBaseModel(content.getMaterialPrefix() + "rune_slab_block_up_down_double", sideUpDownModelTex, topbotModelTex, SlabType.DOUBLE);

            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){

                DeferredHolder<Block, Block> blockRegistryObject = series.NULLZONE_RUNE_SLAB;

                getVariantBuilder(blockRegistryObject.get()).forAllStates(blockState -> {

                    ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

                    BlockModelBuilder compositeBase;
                    String adittive;

                    if (blockState.getValue(ModBlockProperties.UP)){
                        if (blockState.getValue(ModBlockProperties.DOWN)){
                            compositeBase = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                                case TOP -> upDownModelTop;
                                case BOTTOM -> upDownModelBottom;
                                case DOUBLE -> upDownModelDouble;
                            };
                            adittive = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                                case TOP -> "_up_down_top_";
                                case BOTTOM -> "_up_down_bottom_";
                                case DOUBLE -> "_up_down_double_";
                            };
                        } else {
                            compositeBase = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                                case TOP -> upModelTop;
                                case BOTTOM -> upModelBottom;
                                case DOUBLE -> upModelDouble;
                            };
                            adittive = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                                case TOP -> "_up_top_";
                                case BOTTOM -> "_up_bottom_";
                                case DOUBLE -> "_up_double_";
                            };
                        }
                    } else if (blockState.getValue(ModBlockProperties.DOWN)){
                        compositeBase = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                            case TOP -> downModelTop;
                            case BOTTOM -> downModelBottom;
                            case DOUBLE -> downModelDouble;
                        };
                        adittive = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                            case TOP -> "_down_top_";
                            case BOTTOM -> "_down_bottom_";
                            case DOUBLE -> "_down_double_";
                        };
                    } else {
                        compositeBase = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                            case TOP -> baseModelTop;
                            case BOTTOM -> baseModelBottom;
                            case DOUBLE -> baseModelDouble;
                        };
                        adittive = switch (blockState.getValue(BlockStateProperties.SLAB_TYPE)){
                            case TOP -> "_base_top_";
                            case BOTTOM -> "_base_bottom_";
                            case DOUBLE -> "_base_double_";
                        };
                    }

                    if (series instanceof NullZoneBlockSeries target){

                        BlockModelBuilder runeModel;

                        if (blockState.getValue(ModBlockProperties.ACTIVE)){
                            ResourceLocation sideEmissiveVarTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + "slab_active");
                            BlockModelBuilder overlayModel = getEmissiveModel(blockRegistryObject.getId().getPath() + adittive + "active_emissive", sideEmissiveVarTex, blockState.getValue(BlockStateProperties.SLAB_TYPE));
                            runeModel = createRuneBlockModel(blockRegistryObject.getId().getPath() + adittive + "slab_active" , sideModelTex, compositeBase, overlayModel);
                        } else {
                            ResourceLocation sideInactiveVarTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + "slab");
                            BlockModelBuilder overlayModel = getCutoutModel(blockRegistryObject.getId().getPath() + adittive + "inactive_overlay", sideInactiveVarTex, blockState.getValue(BlockStateProperties.SLAB_TYPE));
                            runeModel = createRuneBlockModel(blockRegistryObject.getId().getPath() + adittive + "slab_inactive" , sideModelTex, compositeBase, overlayModel);
                        }

                        if (blockState.getValue(ModBlockProperties.ACTIVE) && !blockState.getValue(BlockStateProperties.WATERLOGGED)){
                            this.getVariantBuilder(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .with(BlockStateProperties.SLAB_TYPE, blockState.getValue(BlockStateProperties.SLAB_TYPE))
                                    .modelForState().modelFile(runeModel).addModel();
                            if (!blockState.getValue(ModBlockProperties.UP) &&
                                    !blockState.getValue(ModBlockProperties.DOWN) &&
                                    blockState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                                simpleBlockItem(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get(), runeModel);
                            }
                        } else if (!blockState.getValue(BlockStateProperties.WATERLOGGED)) {
                            this.getVariantBuilder(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .with(BlockStateProperties.SLAB_TYPE, blockState.getValue(BlockStateProperties.SLAB_TYPE))
                                    .modelForState().modelFile(runeModel).addModel();
                            if (!blockState.getValue(ModBlockProperties.UP) &&
                                    !blockState.getValue(ModBlockProperties.DOWN) &&
                                    blockState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                                simpleBlockItem(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get(), runeModel);
                            }
                        }

                        builder = builder.modelFile(runeModel);
                        if (!blockState.getValue(ModBlockProperties.UP) &&
                                !blockState.getValue(ModBlockProperties.DOWN) &&
                                !blockState.getValue(ModBlockProperties.ACTIVE) &&
                                blockState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM){
                            simpleBlockItem(blockRegistryObject.get(), runeModel);
                        }

                    } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target){
                        builder.modelFile(compositeBase);
                        if (blockState.getValue(ModBlockProperties.ACTIVE) && !blockState.getValue(BlockStateProperties.WATERLOGGED)){
                            this.getVariantBuilder(target.NULLZONE_DECORATIVE_RUNE_SLAB.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .with(BlockStateProperties.SLAB_TYPE, blockState.getValue(BlockStateProperties.SLAB_TYPE))
                                    .modelForState().modelFile(compositeBase).addModel();
                            if (!blockState.getValue(ModBlockProperties.UP) &&
                                    !blockState.getValue(ModBlockProperties.DOWN) &&
                                    blockState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                                simpleBlockItem(target.NULLZONE_DECORATIVE_RUNE_SLAB.get(), compositeBase);
                            }

                            if (!blockState.getValue(ModBlockProperties.UP) &&
                                    !blockState.getValue(ModBlockProperties.DOWN) &&
                                    blockState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                                simpleBlockItem(blockRegistryObject.get(), compositeBase);
                            }
                        }
                    }

                    return builder.build();
                });
            }
        }
    }

    private void varWeightRuneBlocks(List<Integer> vars){

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){

            ResourceLocation topbotModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_topbot");
            ResourceLocation sideModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune");
            ResourceLocation sideUpModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_up");
            ResourceLocation sideDownModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_down");
            ResourceLocation sideUpDownModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_up_down");

            BlockModelBuilder baseModel = getBaseModel(content.getMaterialPrefix() + "rune_block_base", sideModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder upModel = getBaseModel(content.getMaterialPrefix() + "rune_block_up", sideUpModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder downModel = getBaseModel(content.getMaterialPrefix() + "rune_block_down", sideDownModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder upDownModel = getBaseModel(content.getMaterialPrefix() + "rune_block_up_down", sideUpDownModelTex, topbotModelTex, SlabType.DOUBLE);

            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()) {

                DeferredHolder<Block, Block> blockRegistryObject = series.NULLZONE_RUNE;

                List<BlockModelBuilder> varActive = new ArrayList<>();
                List<BlockModelBuilder> varInactive = new ArrayList<>();

                if (series instanceof NullZoneBlockSeries target) {
                    for (int i = 1; i <= vars.size(); i++) {
                        varActive.add(getEmissiveModel(blockRegistryObject.getId().getPath() + "_active_var" + i + "_active_emissive",
                                ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + "var" + i + "_active"),
                                SlabType.DOUBLE));
                        varInactive.add(getCutoutModel(blockRegistryObject.getId().getPath() + "_var" + i + "_inactive_overlay",
                                ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + "var" + i),
                                SlabType.DOUBLE));
                    }
                }

                getVariantBuilder(blockRegistryObject.get()).forAllStates(blockState -> {

                    ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

                    BlockModelBuilder compositeBase;
                    String directions;

                    if (blockState.getValue(ModBlockProperties.UP)) {
                        if (blockState.getValue(ModBlockProperties.DOWN)) {
                            compositeBase = upDownModel;
                            directions = "_up_down_";
                        } else {
                            compositeBase = upModel;
                            directions = "_up_";
                        }
                    } else if (blockState.getValue(ModBlockProperties.DOWN)) {
                        compositeBase = downModel;
                        directions = "_down_";
                    } else {
                        compositeBase = baseModel;
                        directions = "_base_";
                    }

                    if (series instanceof NullZoneBlockSeries target) {

                        simpleBlockItem(target.NULLZONE_RUNE.get(), createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "item", sideModelTex, baseModel, varInactive.get(0)));
                        simpleBlockItem(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "item", sideModelTex, baseModel, varActive.get(0)));
                        simpleBlockItem(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "item", sideModelTex, baseModel, varInactive.get(0)));

                        ConfiguredModel.Builder<?> activeDecBuilder = ConfiguredModel.builder();
                        ConfiguredModel.Builder<?> inactiveDecBuilder = ConfiguredModel.builder();

                        for (int i = 1; i <= vars.size(); i++) {

                            BlockModelBuilder runeModel;

                            if (blockState.getValue(ModBlockProperties.ACTIVE)) {
                                runeModel = createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "var" + i + "_active",
                                        sideModelTex, compositeBase, varActive.get(i - 1));
                                if (i != 1) activeDecBuilder = activeDecBuilder.nextModel();
                                activeDecBuilder = activeDecBuilder.modelFile(runeModel).weight(vars.get(i - 1));
                            } else {
                                runeModel = createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "var" + i + "_inactive",
                                        sideModelTex, compositeBase, varInactive.get(i - 1));
                                if (i != 1) inactiveDecBuilder = inactiveDecBuilder.nextModel();
                                inactiveDecBuilder = inactiveDecBuilder.modelFile(runeModel).weight(vars.get(i - 1));
                            }

                            if (i != 1) builder = builder.nextModel();
                            builder = builder.modelFile(runeModel).weight(vars.get(i - 1));
                        }

                        if (blockState.getValue(ModBlockProperties.ACTIVE)) {
                            this.getVariantBuilder(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .addModels(activeDecBuilder.build());
                        } else {
                            this.getVariantBuilder(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .addModels(inactiveDecBuilder.build());
                        }

                    } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target) {
                        builder.modelFile(compositeBase);
                        simpleBlockItem(blockRegistryObject.get(), compositeBase);
                        if (blockState.getValue(ModBlockProperties.ACTIVE)) {
                            this.getVariantBuilder(target.NULLZONE_DECORATIVE_RUNE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .modelForState().modelFile(compositeBase).addModel();
                            if (!blockState.getValue(ModBlockProperties.UP) && !blockState.getValue(ModBlockProperties.DOWN)) {
                                simpleBlockItem(target.NULLZONE_DECORATIVE_RUNE.get(), compositeBase);
                            }
                        }
                    }

                    return builder.build();
                });
            }
        }
    }

    private void runePillarBlocks(){

        List<NullZoneBlockMaterials> brickMats = List.of(
                NullZoneBlockMaterials.STONE_BRICKS,
                NullZoneBlockMaterials.QUARTZ_BRICKS,
                NullZoneBlockMaterials.NETHER_BRICKS,
                NullZoneBlockMaterials.MUD_BRICKS,
                NullZoneBlockMaterials.BLACKSTONE_BRICKS
        );

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){

            ResourceLocation topbotModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "nullzone_rune_topbot");
            ResourceLocation sideModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "pillar_nullzone_rune");
            ResourceLocation sideUpModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "pillar_nullzone_rune_up");
            ResourceLocation sideDownModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "pillar_nullzone_rune_down");
            ResourceLocation sideUpDownModelTex = ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + content.getMaterialPrefix() + "pillar_nullzone_rune_up_down");

            BlockModelBuilder baseModel = getBaseModel(content.getMaterialPrefix() + "pillar_rune_block_base", sideModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder upModel = getBaseModel(content.getMaterialPrefix() + "pillar_rune_block_up", sideUpModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder downModel = getBaseModel(content.getMaterialPrefix() + "pillar_rune_block_down", sideDownModelTex, topbotModelTex, SlabType.DOUBLE);
            BlockModelBuilder upDownModel = getBaseModel(content.getMaterialPrefix() + "pillar_rune_block_up_down", sideUpDownModelTex, topbotModelTex, SlabType.DOUBLE);

            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()) {

                DeferredHolder<Block, Block> blockRegistryObject = series.NULLZONE_RUNE_PILLAR;

                String additive = brickMats.contains(content.getMaterialEnum()) ? "bricks_" : "";

                BlockModelBuilder active;
                BlockModelBuilder inactive;

                if (!(series instanceof NullZoneBlockSeriesDecorativeStatic)){
                    active = getEmissiveModel(blockRegistryObject.getId().getPath() + additive + "pillar_active_emissive",
                            ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + additive + "pillar_active"),
                            ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + additive + "pillar_topbot_active"));
                    inactive = getCutoutModel(blockRegistryObject.getId().getPath() + additive + "pillar_inactive_overlay",
                            ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + additive + "pillar"),
                            ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, "block/" + series.getPrefix() + additive + "pillar_topbot"));
                } else {
                    inactive = null;
                    active = null;
                }

                getVariantBuilder(blockRegistryObject.get()).forAllStates(blockState -> {

                    ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

                    BlockModelBuilder compositeBase;
                    String directions;

                    if (blockState.getValue(ModBlockProperties.UP)) {
                        if (blockState.getValue(ModBlockProperties.DOWN)) {
                            compositeBase = upDownModel;
                            directions = "_up_down_";
                        } else {
                            compositeBase = upModel;
                            directions = "_up_";
                        }
                    } else if (blockState.getValue(ModBlockProperties.DOWN)) {
                        compositeBase = downModel;
                        directions = "_down_";
                    } else {
                        compositeBase = baseModel;
                        directions = "_base_";
                    }

                    if (series instanceof NullZoneBlockSeries target) {

                        simpleBlockItem(target.NULLZONE_RUNE_PILLAR.get(), createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "item", sideModelTex, baseModel, inactive));
                        simpleBlockItem(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "item", sideModelTex, baseModel, active));
                        simpleBlockItem(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "item", sideModelTex, baseModel, inactive));

                        BlockModelBuilder runeModel;

                        if (blockState.getValue(ModBlockProperties.ACTIVE)) {
                            runeModel = createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "_active",
                                    sideModelTex, compositeBase, active);
                        } else {
                            runeModel = createRuneBlockModel(blockRegistryObject.getId().getPath() + directions + "_inactive",
                                    sideModelTex, compositeBase, inactive);
                        }

                        if (blockState.getValue(ModBlockProperties.ACTIVE)) {
                            this.getVariantBuilder(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .modelForState().modelFile(runeModel).addModel();
                        } else {
                            this.getVariantBuilder(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .modelForState().modelFile(runeModel).addModel();
                        }

                        builder = builder.modelFile(runeModel);

                    } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target) {
                        builder.modelFile(compositeBase);
                        simpleBlockItem(blockRegistryObject.get(), compositeBase);
                        if (blockState.getValue(ModBlockProperties.ACTIVE)) {
                            this.getVariantBuilder(target.PILLAR_NULLZONE_DECORATIVE_RUNE.get()).partialState()
                                    .with(ModBlockProperties.UP, blockState.getValue(ModBlockProperties.UP))
                                    .with(ModBlockProperties.DOWN, blockState.getValue(ModBlockProperties.DOWN))
                                    .modelForState().modelFile(compositeBase).addModel();
                            if (!blockState.getValue(ModBlockProperties.UP) && !blockState.getValue(ModBlockProperties.DOWN)) {
                                simpleBlockItem(target.PILLAR_NULLZONE_DECORATIVE_RUNE.get(), compositeBase);
                            }
                        }
                    }

                    return builder.build();
                });
            }
        }
    }

    private BlockModelBuilder createRuneBlockModel(String name, ResourceLocation particle, BlockModelBuilder base, BlockModelBuilder overlay){
        return models().getBuilder(name)
                .customLoader(CompositeModelBuilder::begin)
                .child("base", base)
                .child("overlay", overlay)
                .end().transforms()
                .transform(ItemDisplayContext.GUI)
                    .rotation(30, 225, 0)
                    .translation(0, 0, 0)
                    .scale(0.625f, 0.625f, 0.625f)
                    .end()
                .transform(ItemDisplayContext.GROUND)
                    .rotation(0, 0, 0)
                    .translation(0, 3, 0)
                    .scale(0.25f, 0.25f, 0.25f)
                    .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                    .rotation(75, 45, 0)
                    .translation(0, 2.5f, 0)
                    .scale(0.375f, 0.375f, 0.375f)
                    .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                    .rotation(0, 45, 0)
                    .translation(0, 0, 0)
                    .scale(0.4f, 0.4f, 0.4f)
                    .end().end().texture("particle", particle);
    }

    private BlockModelBuilder getBaseModel(String path, ResourceLocation sideModelTex, ResourceLocation topbotModelTex, SlabType slabType){

        BlockModelBuilder model = models().getBuilder(path)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", sideModelTex)
                .texture("side", sideModelTex)
                .texture("topbot", topbotModelTex);

        if (slabType == SlabType.DOUBLE){
            model.element()
                    .from(0f, 0, 0f)
                    .to(16f, 16, 16f)
                    .allFaces((direction, faceBuilder) -> {
                        if (direction == Direction.DOWN || direction == Direction.UP){
                            faceBuilder.texture("#topbot").end();
                        } else {
                            faceBuilder.texture("#side").end();
                        }
                    }).end();

            return model;
        } else if (slabType == SlabType.BOTTOM){
            model.element()
                    .from(0f, 0, 0f)
                    .to(16f, 8, 16f)
                    .allFaces((direction, faceBuilder) -> {
                        if (direction == Direction.DOWN || direction == Direction.UP){
                            faceBuilder.texture("#topbot").end();
                        } else {
                            faceBuilder.texture("#side").uvs(0, 8, 16, 16).end();
                        }
                    }).end();

            return model;
        } else {
            model.element()
                    .from(0f, 8, 0f)
                    .to(16f, 16, 16f)
                    .allFaces((direction, faceBuilder) -> {
                        if (direction == Direction.DOWN || direction == Direction.UP){
                            faceBuilder.texture("#topbot").end();
                        } else {
                            faceBuilder.texture("#side").uvs(0, 0, 16, 8).end();
                        }
                    }).end();

            return model;
        }
    }

    private BlockModelBuilder getEmissiveModel(String path, ResourceLocation sideEmissiveModelTex, SlabType slabType){

        BlockModelBuilder model = models().getBuilder(path)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("emissive", sideEmissiveModelTex)
                .renderType("minecraft:translucent");

        if (slabType == SlabType.DOUBLE){
            model.element()
                    .from(-0.01f, -0.01f, -0.01f)
                    .to(16.01f, 16.01f, 16.01f)
                    .shade(false)
                    .emissivity(12, 12)
                    .allFacesExcept((direction, faceBuilder) -> {
                        faceBuilder.texture("#emissive").end();
                    }, Set.of(Direction.UP, Direction.DOWN)).end();

            return model;
        } else if (slabType == SlabType.BOTTOM){
            model.element()
                    .from(-0.01f, -0.01f, -0.01f)
                    .to(16.01f, 8.01f, 16.01f)
                    .shade(false)
                    .emissivity(12, 12)
                    .allFacesExcept((direction, faceBuilder) -> {
                        faceBuilder.texture("#emissive").uvs(0, 8, 16, 16).end();
                    }, Set.of(Direction.UP, Direction.DOWN)).end();

            return model;
        } else {
            model.element()
                    .from(-0.01f, 8 -0.01f, -0.01f)
                    .to(16.01f, 16.01f, 16.01f)
                    .shade(false)
                    .emissivity(12, 12)
                    .allFacesExcept((direction, faceBuilder) -> {
                        faceBuilder.texture("#emissive").uvs(0, 0, 16, 8).end();
                    }, Set.of(Direction.UP, Direction.DOWN)).end();

            return model;
        }
    }

    private BlockModelBuilder getEmissiveModel(String path, ResourceLocation sideEmissiveModelTex, ResourceLocation topbotEmissiveModelTex){

        BlockModelBuilder model = models().getBuilder(path)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", sideEmissiveModelTex)
                .texture("side", sideEmissiveModelTex)
                .texture("topbot", topbotEmissiveModelTex)
                .renderType("minecraft:translucent");

        model.element()
                .from(-0.01f, -0.01f, -0.01f)
                .to(16.01f, 16.01f, 16.01f)
                .shade(false)
                .emissivity(12, 12)
                .allFaces((direction, faceBuilder) -> {
                    if (direction == Direction.DOWN || direction == Direction.UP){
                        faceBuilder.texture("#topbot").end();
                    } else {
                        faceBuilder.texture("#side").end();
                    }
                }).end();

        return model;
    }

    private BlockModelBuilder getCutoutModel(String path, ResourceLocation sideEmissiveModelTex, SlabType slabType){

        BlockModelBuilder model = models().getBuilder(path)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", sideEmissiveModelTex)
                .texture("cutout", sideEmissiveModelTex)
                .renderType("minecraft:translucent");

        if (slabType == SlabType.DOUBLE){
            model.element()
                    .from(-0.01f, -0.01f, -0.01f)
                    .to(16.01f, 16.01f, 16.01f)
                    .allFacesExcept((direction, faceBuilder) -> {
                        faceBuilder.texture("#cutout");
                    }, Set.of(Direction.UP, Direction.DOWN)).end();

            return model;
        } else if (slabType == SlabType.BOTTOM){
            model.element()
                    .from(-0.01f, -0.01f, -0.01f)
                    .to(16.01f, 8.01f, 16.01f)
                    .allFacesExcept((direction, faceBuilder) -> {
                        faceBuilder.texture("#cutout").uvs(0, 8, 16, 16);
                    }, Set.of(Direction.UP, Direction.DOWN)).end();

            return model;
        } else {
            model.element()
                    .from(-0.01f, 7.99f, -0.01f)
                    .to(16.01f, 16.01f, 16.01f)
                    .allFacesExcept((direction, faceBuilder) -> {
                        faceBuilder.texture("#cutout").uvs(0, 0, 16, 8);
                    }, Set.of(Direction.UP, Direction.DOWN)).end();

            return model;
        }
    }

    private BlockModelBuilder getCutoutModel(String path, ResourceLocation sideEmissiveModelTex, ResourceLocation topbotEmissiveModelTex){

        BlockModelBuilder model = models().getBuilder(path)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", sideEmissiveModelTex)
                .texture("side", sideEmissiveModelTex)
                .texture("topbot", topbotEmissiveModelTex)
                .renderType("minecraft:translucent");

        model.element()
                .from(-0.01f, -0.01f, -0.01f)
                .to(16.01f, 16.01f, 16.01f)
                .allFaces((direction, faceBuilder) -> {
                    if (direction == Direction.DOWN || direction == Direction.UP){
                        faceBuilder.texture("#topbot").end();
                    } else {
                        faceBuilder.texture("#side").end();
                    }
                }).end();

        return model;
    }
}
