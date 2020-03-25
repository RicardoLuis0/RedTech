package com.ricardoredstone.redtech.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ModCommonMachineTileEntity extends LockableTileEntity implements ITickableTileEntity , INamedContainerProvider {
    protected ModCommonMachineTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
