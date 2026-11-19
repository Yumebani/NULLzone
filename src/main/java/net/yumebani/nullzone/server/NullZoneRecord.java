package net.yumebani.nullzone.server;

import net.minecraft.core.BlockPos;

public record NullZoneRecord(BlockPos anchor, int distX, int distZ, int distY) {
}
