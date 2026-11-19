package net.yumebani.nullzone.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public enum NullZoneBlockMaterials {
    STONE("stone_", "(Stone)", Blocks.STONE, Blocks.STONE_SLAB),
    STONE_BRICKS("stone_bricks_", "(Stone Bricks)", Blocks.STONE_BRICKS, Blocks.STONE_BRICK_SLAB),
    SANDSTONE("sandstone_", "(Sandstone)", Blocks.SANDSTONE, Blocks.SANDSTONE_SLAB),
    SANDSTONE_BRICKS("sandstone_bricks_", "(Sandstone)", Blocks.CUT_SANDSTONE, Blocks.CUT_SANDSTONE_SLAB),
    PACKED_MUD("packed_mud_", "(Packed Mud)", Blocks.PACKED_MUD, Blocks.PACKED_MUD),
    MUD_BRICKS("mud_bricks_", "(Mud Bricks)", Blocks.MUD_BRICKS, Blocks.MUD_BRICK_SLAB),
    QUARTZ("quartz_", "(Quartz)", Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_SLAB),
    QUARTZ_BRICKS("quartz_bricks_", "(Quartz Bricks)", Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_SLAB),
    NETHERRACK("netherrack_", "(Netherrack)", Blocks.NETHERRACK, Blocks.NETHERRACK),
    NETHER_BRICKS("nether_bricks_", "(Nether Bricks)", Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_SLAB),
    BLACKSTONE("blackstone_", "(Blackstone)", Blocks.BLACKSTONE, Blocks.BLACKSTONE_SLAB),
    BLACKSTONE_BRICKS("blackstone_bricks_", "(Blackstone Bricks)", Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);

    private String text;
    private String title;
    private Block material;
    private Block materialSlab;

    NullZoneBlockMaterials(String text, String title, Block material, Block materialSlab){
        this.text = text;
        this.title = title;
        this.material = material;
        this.materialSlab = materialSlab;
    }

    @Override
    public String toString() {
        return text;
    }

    public String toTitle() {
        return title;
    }

    public Block toMaterial(){return material;}

    public Block toMaterialSlab(){return materialSlab;}
}
