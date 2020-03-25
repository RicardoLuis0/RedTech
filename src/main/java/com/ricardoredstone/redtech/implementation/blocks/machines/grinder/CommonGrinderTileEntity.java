package com.ricardoredstone.redtech.implementation.blocks.machines.grinder;

import com.ricardoredstone.redtech.base.ModCraftingMachineTileEntity;
import com.ricardoredstone.redtech.implementation.recipes.GrindingRecipe;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class CommonGrinderTileEntity extends ModCraftingMachineTileEntity<CommonGrinderTileEntity,GrindingRecipe> {

    protected final NonNullList<ItemStack> inventory;

    @SuppressWarnings("unused")
    protected CommonGrinderTileEntity(TileEntityType<?> tileEntityTypeIn) {
        this(tileEntityTypeIn,0);
    }

    protected CommonGrinderTileEntity(TileEntityType<?> tileEntityTypeIn, int extraSize) {
        super(tileEntityTypeIn,GrindingRecipe.TYPE);
        inventory=NonNullList.withSize(2+extraSize,ItemStack.EMPTY);
    }

    @Override
    protected void init_slots(){
        SLOTS_UP = new int[]{0};
        SLOTS_DOWN = new int[]{1};
        SLOTS_NORTH = SLOTS_UP;
        SLOTS_SOUTH = SLOTS_NORTH;
        SLOTS_EAST = SLOTS_SOUTH;
        SLOTS_WEST = SLOTS_EAST;
    }

    public ItemStack getInput(){
        return inventory.get(0);
    }

    public ItemStack getOutput(){
        return inventory.get(1);
    }

    public ItemStack setInput(ItemStack s){
        input_invalid=false;
        return inventory.set(0,s);
    }

    public void setOutput(ItemStack s){
        output_valid=false;
        inventory.set(1, s);
    }

    @Override
    public int getSizeInventory(){
        return inventory.size();
    }

    @Override
    public void read(CompoundNBT compound){
        super.read(compound);
        inventory.clear();
        ItemStackHelper.loadAllItems(compound, inventory);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound){
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, inventory);
        return compound;
    }

    @Override
    public NonNullList<ItemStack> getInventoryList(){
        return inventory;
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    protected  CommonGrinderTileEntity getInventoryContainer(){
        return this;
    }

    protected boolean canInsertSlot(int slot,ItemStack stack){
        if (slot == 0) {//input
            if (getInput().isEmpty()) {
                assert world != null;
                GrindingRecipe recipe = world.getRecipeManager().getRecipe(GrindingRecipe.TYPE, new Inventory(stack), world).orElse(null);
                return (recipe != null);
            } else {
                return (Container.areItemsAndTagsEqual(stack, getInput()) && getInput().getCount() < getInput().getMaxStackSize());
            }
        }
        return false;
    }

    protected boolean canExtractSlot(int slot,ItemStack stack) {
        return slot==1;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, @Nullable Direction direction) {
        return canInsertSlot(slot,stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, Direction direction) {
        return canExtractSlot(slot,stack);
    }

    @Override
    protected int getCraftTime() {
        return currentRecipe!=null?currentRecipe.getGrindingTime():0;
    }

    @Override
    protected void craft() {
        ItemStack result=currentRecipe.getRecipeOutput();
        ItemStack output=getOutput();
        if(output.isEmpty()){
            setOutput(result.copy());
        }else if(output.isItemEqual(result)||((getOutput().getCount()+result.getCount())<=result.getMaxStackSize())){
            output.grow(result.getCount());
        }else{
            return;
        }
        getInput().shrink(1);
        output_valid=false;
    }

    protected boolean input_invalid;//should be reset when items are moved from input
    protected boolean output_valid;//should be reset when items are moved from output

    @Override
    protected boolean isInvalid() {
        return getInput().isEmpty()||input_invalid;
    }

    @Override
    protected void invalidate() {
        input_invalid=true;
    }

    @Override
    protected boolean canCraft(GrindingRecipe recipe){
        if(output_valid&&recipe==currentRecipe){
            return true;
        }
        ItemStack result=recipe.getRecipeOutput();
        return output_valid= getOutput().isEmpty() || (getOutput().isItemEqual(result) && ((getOutput().getCount() + result.getCount()) <= result.getMaxStackSize()));
    }

}
