package com.ricardoredstone.redtech.base;

import com.mojang.datafixers.types.Type;
import com.ricardoredstone.redtech.RedTechMod;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModTileEntityTypeContainer<T extends TileEntity> implements ModObject {
    private final TileEntityType<T> type;

    public ModTileEntityTypeContainer(String name, Supplier<T> factoryIn, Type<?> datafixerType, Block... validBlocks){
        (type=TileEntityType.Builder.create(factoryIn, validBlocks).build(datafixerType)).setRegistryName(RedTechMod.makeResourceLocation(name));
    }

    @SuppressWarnings("ConstantConditions")
    public ModTileEntityTypeContainer(String name, Supplier<T> factoryIn, Block... validBlocks){
        this(name,factoryIn,null,validBlocks);
    }

    public TileEntityType<T> getType() {
        return type;
    }

    @Override
    public void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(type);
    }

}
