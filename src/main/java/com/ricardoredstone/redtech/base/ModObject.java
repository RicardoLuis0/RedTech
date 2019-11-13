package com.ricardoredstone.redtech.base;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface ModObject {

    default void setupCommon(final FMLCommonSetupEvent event){
        //do nothing
    }

    default void setupClient(final FMLClientSetupEvent event){
        //do nothing
    }

    default void registerBlocks(final RegistryEvent.Register<Block> event) {
        //do nothing
    }

    default void registerItems(final RegistryEvent.Register<Item> event) {
        //do nothing
    }

    default void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        //do nothing
    }

}
