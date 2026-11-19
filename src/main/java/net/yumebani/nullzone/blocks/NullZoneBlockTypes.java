package net.yumebani.nullzone.blocks;

public enum NullZoneBlockTypes {
    BASE("", ""),
    COAL("coal_", "Coal"),
    COPPER("copper_", "Copper"),
    IRON("iron_", "Iron"),
    AMETHYST("amethyst_", "Amethyst"),
    GOLD("gold_", "Gold"),
    DIAMOND("diamond_", "Diamond"),
    EMERALD("emerald_", "Emeralds");

    private String text;
    private String title;

    NullZoneBlockTypes(String text, String title){
        this.text = text;
        this.title = title;
    }

    @Override
    public String toString() {
        return text;
    }

    public String toTitle() {
        return title;
    }
}
