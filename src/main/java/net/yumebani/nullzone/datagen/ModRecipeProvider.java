package net.yumebani.nullzone.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.*;
import net.yumebani.nullzone.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    private void buildDiamondCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.DIAMOND_BLOCK, 1)
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK))
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildEmeraldCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.EMERALD_BLOCK, 1)
                .unlockedBy(getHasName(Items.EMERALD_BLOCK), has(Items.EMERALD_BLOCK))
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildGoldCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.GOLD_BLOCK, 3)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildAmethystCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.AMETHYST_BLOCK, 8)
                .unlockedBy(getHasName(Items.AMETHYST_BLOCK), has(Items.AMETHYST_BLOCK))
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildIronCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.IRON_BLOCK, 5)
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildCopperCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.COPPER_BLOCK, 8)
                .unlockedBy(getHasName(Items.COPPER_BLOCK), has(Items.COPPER_BLOCK))
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildCoalCrystalRecipe(Block result, Block base, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(base, 1)
                .requires(Items.COAL_BLOCK, 8)
                .unlockedBy(getHasName(Items.COAL_BLOCK), has(Items.COAL_BLOCK))
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + name);
    }


    private void buildDiamondRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 8)
                .requires(material, 7)
                .requires(Items.DIAMOND, 1)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildEmeraldRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 8)
                .requires(material, 7)
                .requires(Items.EMERALD, 1)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildGoldRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 5)
                .requires(material, 4)
                .requires(Items.GOLD_INGOT, 1)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildAmethystRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 3)
                .requires(material, 2)
                .requires(Items.AMETHYST_SHARD, 1)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildIronRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 3)
                .requires(material, 2)
                .requires(Items.IRON_INGOT, 1)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildCopperRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 2)
                .requires(material, 2)
                .requires(Items.COPPER_INGOT, 2)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    private void buildCoalRuneRecipe(Block result, Block material, String name, RecipeOutput consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 2)
                .requires(material, 2)
                .requires(Items.COAL, 2)
                .requires(Items.GLOWSTONE, 1)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(consumer, NullZoneMod.MODID + name);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()) {

            buildDiamondCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.DIAMOND)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.DIAMOND).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.DIAMOND)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);
            buildEmeraldCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.EMERALD)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.EMERALD).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.EMERALD)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);
            buildGoldCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.GOLD)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.GOLD).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.GOLD)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);
            buildAmethystCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.AMETHYST)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.AMETHYST).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.AMETHYST)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);
            buildIronCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.IRON)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.IRON).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.IRON)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);
            buildCopperCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.COPPER)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.COPPER).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.COPPER)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);
            buildCoalCrystalRecipe(((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.COAL)).NULLZONE_CRYSTAL.get(), content.CONTENTS.get(NullZoneBlockTypes.COAL).NULLZONE_RUNE_SLAB.get(), ((NullZoneBlockSeries) content.CONTENTS.get(NullZoneBlockTypes.COAL)).NULLZONE_CRYSTAL.getId().getPath() + "_crafting", consumer);

            buildDiamondRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.DIAMOND).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.DIAMOND).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);
            buildEmeraldRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.EMERALD).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.EMERALD).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);
            buildGoldRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.GOLD).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.GOLD).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);
            buildAmethystRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.AMETHYST).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.AMETHYST).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);
            buildIronRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.IRON).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.IRON).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);
            buildCopperRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.COPPER).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.COPPER).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);
            buildCoalRuneRecipe(content.CONTENTS.get(NullZoneBlockTypes.COAL).NULLZONE_RUNE.get(), content.getMaterial(), content.CONTENTS.get(NullZoneBlockTypes.COAL).NULLZONE_RUNE.getId().getPath() + "_crafting", consumer);

            Ingredient runes = Ingredient.of(
                    content.CONTENTS.get(NullZoneBlockTypes.DIAMOND).NULLZONE_RUNE.get(),
                    content.CONTENTS.get(NullZoneBlockTypes.EMERALD).NULLZONE_RUNE.get(),
                    content.CONTENTS.get(NullZoneBlockTypes.GOLD).NULLZONE_RUNE.get(),
                    content.CONTENTS.get(NullZoneBlockTypes.AMETHYST).NULLZONE_RUNE.get(),
                    content.CONTENTS.get(NullZoneBlockTypes.IRON).NULLZONE_RUNE.get(),
                    content.CONTENTS.get(NullZoneBlockTypes.COPPER).NULLZONE_RUNE.get(),
                    content.CONTENTS.get(NullZoneBlockTypes.COAL).NULLZONE_RUNE.get());

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, content.CONTENTS.get(NullZoneBlockTypes.BASE).NULLZONE_RUNE.get(), 1)
                    .requires(runes)
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.DIAMOND).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.DIAMOND).NULLZONE_RUNE.get()))
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.EMERALD).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.EMERALD).NULLZONE_RUNE.get()))
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.GOLD).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.GOLD).NULLZONE_RUNE.get()))
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.AMETHYST).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.AMETHYST).NULLZONE_RUNE.get()))
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.IRON).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.IRON).NULLZONE_RUNE.get()))
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.COPPER).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.COPPER).NULLZONE_RUNE.get()))
                    .unlockedBy(getHasName(content.CONTENTS.get(NullZoneBlockTypes.COAL).NULLZONE_RUNE.get()), has(content.CONTENTS.get(NullZoneBlockTypes.COAL).NULLZONE_RUNE.get()))
                    .save(consumer, NullZoneMod.MODID + content.getMaterialPrefix() + "base_rune_crafting");

            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){

                slabAndUnslabBlock(consumer, series.NULLZONE_RUNE.get(), series.NULLZONE_RUNE_SLAB.get(), series.getMaterialPrefix() + series.getPrefix());
                pillarAndUnpillarBlock(consumer, series.NULLZONE_RUNE.get(), series.NULLZONE_RUNE_PILLAR.get(), series.getMaterialPrefix() + series.getPrefix());

                if (series instanceof NullZoneBlockSeries target){

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), 2)
                            .requires(content.getMaterial(), 1)
                            .requires(target.NULLZONE_RUNE.get(), 1)
                            .unlockedBy(getHasName(target.NULLZONE_RUNE.get()), has(target.NULLZONE_RUNE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_rune_crafting");

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), 1)
                            .requires(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), 1)
                            .unlockedBy(getHasName(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get()), has(target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_rune_activation");

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), 1)
                            .requires(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), 1)
                            .unlockedBy(getHasName(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get()), has(target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_rune_deactivation");

                    slabAndUnslabBlock(consumer, target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get(), series.getMaterialPrefix() + target.getPrefix() + "decorative_inactive_");
                    slabAndUnslabBlock(consumer, target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get(), series.getMaterialPrefix() + target.getPrefix() + "decorative_active_");

                    pillarAndUnpillarBlock(consumer, target.NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), series.getMaterialPrefix() + target.getPrefix() + "decorative_inactive_");
                    pillarAndUnpillarBlock(consumer, target.NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), series.getMaterialPrefix() + target.getPrefix() + "decorative_active_");

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get(), 1)
                            .requires(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get(), 1)
                            .unlockedBy(getHasName(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get()), has(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_slab_rune_activation");

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE.get(), 1)
                            .requires(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get(), 1)
                            .unlockedBy(getHasName(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get()), has(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_slab_rune_deactivation");

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), 1)
                            .requires(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), 1)
                            .unlockedBy(getHasName(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get()), has(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_pillar_rune_activation");

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE.get(), 1)
                            .requires(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get(), 1)
                            .unlockedBy(getHasName(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get()), has(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() +  target.getPrefix() + "decorative_pillar_rune_deactivation");

                } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target){

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.NULLZONE_DECORATIVE_RUNE.get(), 2)
                            .requires(Items.STONE, 1)
                            .requires(target.NULLZONE_RUNE.get(), 1)
                            .unlockedBy(getHasName(target.NULLZONE_RUNE.get()), has(target.NULLZONE_RUNE.get()))
                            .save(consumer, NullZoneMod.MODID + ":" + series.getMaterialPrefix() + "decorative_rune_crafting");

                    slabAndUnslabBlock(consumer, target.NULLZONE_DECORATIVE_RUNE.get(), target.NULLZONE_DECORATIVE_RUNE_SLAB.get(), series.getMaterialPrefix() + "decorative_");

                    pillarAndUnpillarBlock(consumer, target.NULLZONE_DECORATIVE_RUNE.get(), target.PILLAR_NULLZONE_DECORATIVE_RUNE.get(), series.getMaterialPrefix() + "decorative_");
                }
            }
        }
    }

    public void slabAndUnslabBlock(RecipeOutput consumer, Block base, Block slab, String prefix){
        slabBlock(consumer, base, slab, prefix);
        unslabBlock(consumer, base, slab, prefix);
    }

    public void slabBlock(RecipeOutput consumer, Block base, Block slab, String prefix){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, slab, 6)
                .define('#', base)
                .pattern("###")
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + ":" + prefix + "rune_slab");
    }

    public void unslabBlock(RecipeOutput consumer, Block base, Block slab, String prefix){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, base, 1)
                .define('#', slab)
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + ":" + prefix + "rune_unslab");
    }

    public void pillarAndUnpillarBlock(RecipeOutput consumer, Block base, Block slab, String prefix){
        pillarBlock(consumer, base, slab, prefix);
        unpillarBlock(consumer, base, slab, prefix);
    }

    public void pillarBlock(RecipeOutput consumer, Block base, Block pillar, String prefix){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pillar, 2)
                .define('#', base)
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + ":" + prefix + "rune_pillar");
    }

    public void unpillarBlock(RecipeOutput consumer, Block base, Block pillar, String prefix){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, base, 2)
                .define('#', pillar)
                .pattern("##")
                .unlockedBy(getHasName(base), has(base))
                .save(consumer, NullZoneMod.MODID + ":" + prefix + "rune_unpillar");
    }
}
