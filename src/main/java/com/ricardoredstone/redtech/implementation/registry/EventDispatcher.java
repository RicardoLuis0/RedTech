package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.base.ModObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;

public class EventDispatcher {

    static final ArrayList<ModObject> registry;

    static {
        registry = new ArrayList<ModObject>();
    }

    public static ModObject addObject(ModObject obj) {
        registry.add(obj);
        return obj;
    }

    public static void setupCommon(final FMLCommonSetupEvent event){
        registry.forEach((e) -> e.setupCommon(event));

    }

    public static void setupClient(final FMLClientSetupEvent event){
        registry.forEach((e) -> e.setupClient(event));
    }

    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        registry.forEach((e) -> e.registerBlocks(event));
    }

    public static void registerItems(final RegistryEvent.Register<Item> event) {
        registry.forEach((e) -> e.registerItems(event));
    }

    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        registry.forEach((e) -> e.registerTileEntities(event));
    }
}
