package net.yumebani.nullzone.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.yumebani.nullzone.NullZoneMod;
import net.yumebani.nullzone.blocks.*;

import static net.minecraft.world.level.block.entity.HopperBlockEntity.addItem;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, NullZoneMod.MODID, "en_us"); // Your mod id and target language locale
    }

    @Override
    protected void addTranslations() {

        add("creativetab.nullzone.functional", "NULLzone");
        add("creativetab.nullzone.decor", "NULLzone Decor");

        for (NullZoneMaterialBlockSeries content : ModBlocks.CONTENTS.values()){

            for (NullZoneBlockSeriesBase series : content.CONTENTS.values()){

                addBlock(series.NULLZONE_RUNE, series.getPrefixTitle() + " NULLzone Rune " + content.getMaterialTitle());
                addBlock(series.NULLZONE_RUNE_SLAB, series.getPrefixTitle() + " NULLzone Rune Slab " + content.getMaterialTitle());
                addBlock(series.NULLZONE_RUNE_PILLAR, series.getPrefixTitle() + " NULLzone Rune Pillar " + content.getMaterialTitle());

                if (series instanceof NullZoneBlockSeries target){
                    addBlock(target.NULLZONE_CRYSTAL, target.getPrefixTitle() + " NULLzone Crystal " + content.getMaterialTitle());
                    addBlock(target.NULLZONE_DECORATIVE_RUNE_INACTIVE, target.getPrefixTitle() + " NULLzone Decorative Rune Inactive " + content.getMaterialTitle());
                    addBlock(target.NULLZONE_DECORATIVE_RUNE_ACTIVE, target.getPrefixTitle() + " NULLzone Decorative Rune Active " + content.getMaterialTitle());
                    addBlock(target.NULLZONE_DECORATIVE_RUNE_SLAB_INACTIVE, target.getPrefixTitle() + " NULLzone Decorative Rune Slab Inactive " + content.getMaterialTitle());
                    addBlock(target.NULLZONE_DECORATIVE_RUNE_SLAB_ACTIVE, target.getPrefixTitle() + " NULLzone Decorative Rune Slab Active " + content.getMaterialTitle());
                    addBlock(target.PILLAR_NULLZONE_DECORATIVE_RUNE_INACTIVE, target.getPrefixTitle() + " NULLzone Decorative Rune Pillar Inactive " + content.getMaterialTitle());
                    addBlock(target.PILLAR_NULLZONE_DECORATIVE_RUNE_ACTIVE, target.getPrefixTitle() + " NULLzone Decorative Rune Pillar Active " + content.getMaterialTitle());
                } else if (series instanceof NullZoneBlockSeriesDecorativeStatic target){
                    addBlock(target.NULLZONE_DECORATIVE_RUNE, target.getPrefixTitle() + " NULLzone Decorative Rune " + content.getMaterialTitle());
                    addBlock(target.NULLZONE_DECORATIVE_RUNE_SLAB, target.getPrefixTitle() + " NULLzone Decorative Rune Slab " + content.getMaterialTitle());
                    addBlock(target.PILLAR_NULLZONE_DECORATIVE_RUNE, target.getPrefixTitle() + " NULLzone Decorative Rune Pillar " + content.getMaterialTitle());
                }
            }
        }
    }
}
