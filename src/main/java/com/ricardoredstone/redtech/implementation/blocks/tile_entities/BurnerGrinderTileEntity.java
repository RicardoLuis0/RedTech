package com.ricardoredstone.redtech.implementation.blocks.tile_entities;

import com.ricardoredstone.redtech.RedTechMod;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class BurnerGrinderTileEntity extends TileEntity implements ITickableTileEntity {
    public BurnerGrinderTileEntity() {
        super(RedTechMod.TILE_ENTITIES.BURNER_GRINDER.getType());
    }

    @Override
    public void tick() {

    }
}
