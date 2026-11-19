package net.yumebani.nullzone.blocks;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public abstract class NullZoneMaterialBlockSeries {

    public abstract NullZoneBlockMaterials getMaterialEnum();
    public abstract String getMaterialPrefix();
    public abstract String getMaterialTitle();
    public abstract Block getMaterial();
    public abstract Block getMaterialSlab();

    public Map<NullZoneBlockTypes, NullZoneBlockSeriesBase> CONTENTS;

    public NullZoneMaterialBlockSeries(){
            CONTENTS = new HashMap<>();

            String materialPrefix = getMaterialPrefix();
            Block material = getMaterial();
            Block materialSlab = getMaterialSlab();

            CONTENTS.put(NullZoneBlockTypes.BASE, new NullZoneBlockSeriesDecorativeStatic(){
                @Override
                public String getPrefix() {
                    return NullZoneBlockTypes.BASE.toString();
                }

                @Override
                public String getPrefixTitle() {
                    return NullZoneBlockTypes.BASE.toTitle();
                }

                @Override
                public String getMaterialPrefix() {
                    return materialPrefix;
                }

                @Override
                public Block getMaterial() {
                    return material;
                }

                @Override
                public Block getMaterialSlab() {
                    return materialSlab;
                }
            });

            for (NullZoneBlockTypes type : NullZoneBlockTypes.values()){

                if (type != NullZoneBlockTypes.BASE) {
                    CONTENTS.put(type, new NullZoneBlockSeries(){
                        @Override
                        public String getPrefix() {
                            return type.toString();
                        }

                        @Override
                        public String getPrefixTitle() {
                            return type.toTitle();
                        }

                        @Override
                        public String getMaterialPrefix() {
                            return materialPrefix;
                        }

                        @Override
                        public Block getMaterial() {
                            return material;
                        }

                        @Override
                        public Block getMaterialSlab() {
                            return materialSlab;
                        }
                    });
                }
            }
    }
}
