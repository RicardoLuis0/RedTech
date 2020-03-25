package com.ricardoredstone.redtech.implementation.blocks.machines.grinder;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModMachine;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BurnerGrinderTileEntity extends CommonGrinderTileEntity {

    protected boolean burning;
    protected int burnTime;
    protected int maxBurnTime;

    protected World _getWorld(){
        assert world != null;
        return world;
    }
    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.redtech.burner_grinder");
    }

    @Override
    public void read(CompoundNBT compound){
        super.read(compound);
        burnTime = compound.getInt("burnTime");
        maxBurnTime = compound.getInt("maxBurnTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound){
        super.write(compound);
        compound.putInt("burnTime",burnTime);
        compound.putInt("maxBurnTime",maxBurnTime);
        return compound;
    }

    protected int _getWorkTime(){
        return workTime;
    }

    protected int _getCraftTime(){
        return craftTime;
    }

    public BurnerGrinderTileEntity() {
        super(RedTechMod.TILE_ENTITIES.BURNER_GRINDER.getType(),1);
        burning=false;
    }

    public ItemStack getFuel() {
        return inventory.get(2);
    }

    public void setFuel(ItemStack f) {
        inventory.set(2, f);
    }

    @Override
    protected void init_slots(){
        SLOTS_UP = new int[]{0};
        SLOTS_DOWN = new int[]{1,2};
        SLOTS_NORTH = new int[]{2};
        SLOTS_SOUTH = SLOTS_NORTH;
        SLOTS_EAST = SLOTS_SOUTH;
        SLOTS_WEST = SLOTS_EAST;
    }

    protected int getFuelBurnTime(){
        return net.minecraftforge.common.ForgeHooks.getBurnTime(getFuel());
    }

    protected boolean hasFuel(){
        return net.minecraftforge.common.ForgeHooks.getBurnTime(getFuel())>0;
    }

    @Override
    protected boolean canWork() {
        return burnTime > 0||hasFuel();//if is lit or has is fuel
    }

    private void consumeFuel() {
        burnTime = getFuelBurnTime();
        maxBurnTime = burnTime;
        ItemStack fuel = getFuel();
        if (fuel.hasContainerItem()) {
            setFuel(fuel.getContainerItem());
        } else {
            fuel.shrink(1);
        }
    }

    @Override
    protected boolean work() {
        assert world != null;
        if(burnTime == 0 && hasFuel()){//consume fuel
            consumeFuel();
        }
        if(burnTime >0) {
            if(!burning){
                world.setBlockState(pos, world.getBlockState(pos).with(ModMachine.LIT, true), 3);
                burning=true;
            }
            burnTime--;
            if(burnTime==0){
                world.setBlockState(pos, world.getBlockState(pos).with(ModMachine.LIT, false), 3);
                burning=false;
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected boolean canInsertSlot(int slot,ItemStack stack){
        if(slot==2) {
            if (getFuel().isEmpty()) {
                return AbstractFurnaceTileEntity.isFuel(stack);
            } else {
                return (Container.areItemsAndTagsEqual(stack, getFuel()) && getFuel().getCount() < getFuel().getMaxStackSize());
            }
        }else{
            return super.canInsertSlot(slot,stack);
        }
    }

    @Override
    protected boolean canExtractSlot(int slot,ItemStack stack) {
        return (slot==2&&!AbstractFurnaceTileEntity.isFuel(stack))||super.canExtractSlot(slot,stack);
    }

    @Override
    protected boolean idleWork() {
        assert world != null;
        if(burnTime >0) {
            burnTime--;
            if(burnTime==0) {
                world.setBlockState(pos, world.getBlockState(pos).with(ModMachine.LIT, false), 3);
                burning = false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new BurnerGrinderContainer(id,playerInventory,this);
    }
}
