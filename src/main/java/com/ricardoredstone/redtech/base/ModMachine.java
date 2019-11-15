package com.ricardoredstone.redtech.base;

import com.ricardoredstone.redtech.RedTechMod;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
abstract public class ModMachine extends AbstractFurnaceBlock implements ModObject {

    private final Item blockItem;

    protected ModMachine(String name, Block.Properties block_properties) {
        this(name,block_properties,new Item.Properties().group(ItemGroup.DECORATIONS));
    }

    protected ModMachine(String name, Block.Properties block_properties, Item.Properties item_properties) {
        super(block_properties);
        ResourceLocation reg_name= RedTechMod.makeResourceLocation(name);
        setRegistryName(reg_name);
        blockItem = new BlockItem(this, item_properties).setRegistryName(reg_name);
    }

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof INamedContainerProvider) {
            player.openContainer((INamedContainerProvider)tileentity);
        }
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
