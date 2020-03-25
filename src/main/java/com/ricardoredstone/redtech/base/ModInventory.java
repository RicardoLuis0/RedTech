package com.ricardoredstone.redtech.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface ModInventory extends IInventory {

    NonNullList<ItemStack> getInventoryList();

    @Override
    default int getSizeInventory() {
        return getInventoryList().size();
    }

    @Override
    default boolean isEmpty() {
        for(ItemStack i: getInventoryList()){
            if(!i.isEmpty())return false;
        }
        return true;
    }

    @Override
    default ItemStack getStackInSlot(int index) {
        return getInventoryList().get(index);
    }

    @Override
    default ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(getInventoryList(),index,count);
    }

    @Override
    default ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(getInventoryList(),index);
    }

    @Override
    default void setInventorySlotContents(int index, ItemStack stack) {
        this.getInventoryList().set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    default void clear() {
        for(int i=0;i<getInventoryList().size();i++){
            getInventoryList().set(i,ItemStack.EMPTY);
        }
    }
}
