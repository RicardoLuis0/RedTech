package com.ricardoredstone.redtech.base;

import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class ModCommonMachineTileEntity extends TileEntity implements ITickableTileEntity , INamedContainerProvider {
    public ModCommonMachineTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
