package com.ricardoredstone.redtech.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ModCraftingMachineTileEntity<V extends IInventory,T extends IRecipe<IInventory>> extends ModInventoryMachineTileEntity {

    protected final IRecipeType<T> recipeType;

    protected T currentRecipe=null;
    protected int workTime;//current time
    protected int craftTime;//goal
    protected boolean crafting;

    protected ModCraftingMachineTileEntity(TileEntityType<?> tileEntityTypeIn,IRecipeType<T> recipeType) {
        super(tileEntityTypeIn);
        this.recipeType=recipeType;
    }

    protected abstract boolean canWork();//can the machine activate? (has fuel/power)
    protected abstract boolean work();//use up fuel/power
    protected abstract boolean idleWork();//use up fuel/power while idle
    protected abstract int getCraftTime();//get work time for current recipe
    protected abstract boolean canCraft(T recipe);
    protected abstract void craft();//finish crafting
    protected abstract boolean isInvalid();
    protected abstract void invalidate();
    protected abstract V getInventoryContainer();

    @Override
    public void read(CompoundNBT compound){
        super.read(compound);
        workTime = compound.getInt("workTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound){
        super.write(compound);
        compound.putInt("workTime",workTime);
        return compound;
    }

    @Override
    public void tick(){
        boolean dirty = false;
        if(canWork()) {
            updateRecipe();
            if (currentRecipe != null) {
                dirty=true;
                if (!work()) {
                    crafting = false;
                    workTime = 0;
                } else {
                    workTime++;
                }
                if (workTime >= craftTime) {
                    craft();
                    crafting = false;
                    workTime = 0;
                }
            } else {
                dirty = idleWork();
                if (crafting) {//was crafting but not anymore
                    crafting = false;
                    workTime = 0;
                    dirty = true;
                }
            }
        } else if(crafting) {
            crafting = false;
            workTime = 0;
            dirty = true;
        }
        if(dirty) {
            markDirty();
        }
    }

    protected final void updateRecipe(){
        if(isInvalid()){
            currentRecipe=null;
            return;
        }
        assert world != null;
        if(currentRecipe!=null&&currentRecipe.matches(getInventoryContainer(),world)){
            if(!canCraft(currentRecipe)){
                currentRecipe=null;
                invalidate();
                return;
            }
        }
        T recipe=world.getRecipeManager().getRecipe(recipeType,getInventoryContainer(),world).orElse(null);
        if(recipe==null){
            invalidate();
            currentRecipe=null;
        }else if(canCraft(recipe)){
            currentRecipe = recipe;
            craftTime = getCraftTime();
            crafting = true;
        }else{
            currentRecipe = null;
            invalidate();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        assert world != null;
        if (world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

}
