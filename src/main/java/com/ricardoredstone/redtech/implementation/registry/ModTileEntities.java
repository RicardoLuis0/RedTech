package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModTileEntityTypeContainer;
import com.ricardoredstone.redtech.implementation.blocks.tile_entities.BurnerGrinderTileEntity;

public final class ModTileEntities {
    public ModTileEntityTypeContainer<BurnerGrinderTileEntity> BURNER_GRINDER = RedTechMod.REGISTRY.add(new ModTileEntityTypeContainer<>("burner_grinder",BurnerGrinderTileEntity::new,RedTechMod.BLOCKS.BURNER_GRINDER));
}
