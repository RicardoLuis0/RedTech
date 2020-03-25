package com.ricardoredstone.redtech.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class TileEntityContainer<T extends TileEntity & IInventory> extends Container {
    protected final T tile_entity;
    protected TileEntityContainer(ContainerType<?> type, int id, T te) {
        super(type, id);
        tile_entity=te;
    }

    public T getTileEntity(){
        return tile_entity;
    }
}
