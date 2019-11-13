package com.ricardoredstone.redtech.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;

public class ModMetal implements ModObject {
    public ModSimpleItem dust;
    public ModSimpleItem nugget;
    public ModSimpleItem ingot;
    public ModSimpleBlock block;

    public ModMetal(String name){
        dust=new ModSimpleItem(name+"_dust", new Item.Properties().group(ItemGroup.MATERIALS));
        nugget=new ModSimpleItem(name+"_nugget", new Item.Properties().group(ItemGroup.MATERIALS));
        ingot=new ModSimpleItem(name+"_ingot", new Item.Properties().group(ItemGroup.MATERIALS));
        block=new ModSimpleBlock(name+"_block", Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL));
    }

    @Override
    public void registerBlocks(final RegistryEvent.Register<Block> event) {
        block.registerBlocks(event);
    }

    @Override
    public void registerItems(final RegistryEvent.Register<Item> event) {
        dust.registerItems(event);
        nugget.registerItems(event);
        ingot.registerItems(event);
        block.registerItems(event);
    }
}
