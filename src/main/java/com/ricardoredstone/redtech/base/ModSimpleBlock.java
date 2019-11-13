package com.ricardoredstone.redtech.base;

import com.ricardoredstone.redtech.RedTechMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class ModSimpleBlock extends Block implements ModObject {

    private final Item blockItem;

    public ModSimpleBlock(String name, Block.Properties block_properties) {
        this(name,block_properties,new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
    }

    public ModSimpleBlock(String name, Block.Properties block_properties, Item.Properties item_properties) {
        super(block_properties);
        ResourceLocation reg_name= RedTechMod.makeResourceLocation(name);
        setRegistryName(reg_name);
        blockItem = new BlockItem(this, item_properties).setRegistryName(reg_name);
    }

    public Item getBlockItem(){
        return blockItem;
    }

    @Override
    public void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(this);
    }

    @Override
    public void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(blockItem);
    }

}
