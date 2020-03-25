package com.ricardoredstone.redtech.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModOreMetal implements ModObject {

    public ModOreBlock ore;
    public ModMetal metal;

    public ModOreMetal(String name, int count, CountRangeConfig spawn_range){
        ore=new ModOreBlock(name,count,spawn_range);
        metal=new ModMetal(name);
    }

    @Override
    public void setupCommon(final FMLCommonSetupEvent event) {
        ore.setupCommon(event);
    }

    @Override
    public void registerBlocks(final RegistryEvent.Register<Block> event) {
        ore.registerBlocks(event);
        metal.registerBlocks(event);
    }

    @Override
    public void registerItems(final RegistryEvent.Register<Item> event) {
        ore.registerItems(event);
        metal.registerItems(event);
    }
}
