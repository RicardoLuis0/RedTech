package com.ricardoredstone.redtech.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;

public class ModMetalMulti implements ModObject {
    ModSimpleItem dust;
    ModSimpleItem nugget;
    ModSimpleItem ingot;
    ModSimpleBlock block;

    public ModMetalMulti(String name){
        dust=new ModSimpleItem(name+"_dust", new Item.Properties().group(ItemGroup.MATERIALS));
        nugget=new ModSimpleItem(name+"_nugget", new Item.Properties().group(ItemGroup.MATERIALS));
        ingot=new ModSimpleItem(name+"_ingot", new Item.Properties().group(ItemGroup.MATERIALS));
        block=new ModSimpleBlock(name+"_block", Block.Properties.create(Material.IRON));
    }

    @Override
    public void registerBlock(final RegistryEvent.Register<Block> event) {
        block.registerBlock(event);
    }

    @Override
    public void registerItem(final RegistryEvent.Register<Item> event) {
        dust.registerItem(event);
        nugget.registerItem(event);
        ingot.registerItem(event);
        block.registerItem(event);
    }
}
