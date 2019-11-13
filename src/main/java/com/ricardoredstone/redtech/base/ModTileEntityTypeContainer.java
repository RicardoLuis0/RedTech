package com.ricardoredstone.redtech.base;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.implementation.blocks.tile_entities.BurnerGrinderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.function.Supplier;

public class ModTileEntityTypeContainer<T extends TileEntity> implements ModObject {
    private final TileEntityType<T> type;
    public ModTileEntityTypeContainer(Supplier<T> factoryIn, Block... validBlocks){//TODO data fixer support
        type=TileEntityType.Builder.create(factoryIn, validBlocks).build(null);
    }

    public TileEntityType<T> getType() {
        return type;
    }

    @Override
    public void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(type);
    }

}
