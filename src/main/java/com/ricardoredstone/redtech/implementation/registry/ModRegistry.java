package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.base.ModObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;

public class ModRegistry {

    private final ArrayList<ModObject> registry;

    public ModRegistry(){
        registry = new ArrayList<>();
    }

    public <T extends ModObject> T add(T obj) {
        registry.add(obj);
        return obj;
    }

    public void setupCommon(final FMLCommonSetupEvent event){
        registry.forEach((e) -> e.setupCommon(event));

    }

    public void setupClient(final FMLClientSetupEvent event){
        registry.forEach((e) -> e.setupClient(event));
    }

    public void registerBlocks(final RegistryEvent.Register<Block> event) {
        registry.forEach((e) -> e.registerBlocks(event));
    }

    public void registerItems(final RegistryEvent.Register<Item> event) {
        registry.forEach((e) -> e.registerItems(event));
    }

    public void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        registry.forEach((e) -> e.registerTileEntities(event));
    }
}
