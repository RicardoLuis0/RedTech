package com.ricardoredstone.redtech.implementation.blocks.tile_entities;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModBurnerMachineTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BurnerGrinderTileEntity extends ModBurnerMachineTileEntity {

    public BurnerGrinderTileEntity() {
        super(RedTechMod.TILE_ENTITIES.BURNER_GRINDER.getType());
    }

    @Override
    public void tick() {
        //throw new RuntimeException("BurnerGrinderTileEntity::tick unimplemented");
    }

    @Override
    public ITextComponent getDisplayName() {
        throw new RuntimeException("BurnerGrinderTileEntity::getDisplayName unimplemented");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        throw new RuntimeException("BurnerGrinderTileEntity::getDisplayName createMenu");
    }
}
