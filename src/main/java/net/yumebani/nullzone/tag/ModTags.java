package net.yumebani.nullzone.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.yumebani.nullzone.NullZoneMod;

public class ModTags {

    public static class Items {


        public static final TagKey<Item> RUNE_RUNE_BLOCKS = tag("rune_rune_blocks");
        public static final TagKey<Item> BRICK_RUNE_BLOCKS = tag("brick_rune_blocks");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(NullZoneMod.MODID, name));
        }
    }
}
