package com.ricardoredstone.redtech.implementation.blocks.machines.grinder;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.TileEntityContainer;
import com.ricardoredstone.redtech.implementation.recipes.GrindingRecipe;
import com.ricardoredstone.redtech.implementation.registry.ModContainers;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BurnerGrinderContainer extends TileEntityContainer<BurnerGrinderTileEntity> /*RecipeBookContainer<?>*/ {
    public BurnerGrinderContainer(int id, PlayerInventory playerInventory, PacketBuffer extraData){
        this(id,playerInventory, (BurnerGrinderTileEntity) Objects.requireNonNull(RedTechMod.PROXY.getWorld().getTileEntity(extraData.readBlockPos())));
    }

    public BurnerGrinderContainer(int id, PlayerInventory playerInventory, BurnerGrinderTileEntity te) {
        super(ModContainers.BURNER_GRINDER,id,te);
        addSlot(new Slot(tile_entity, 0, 56,17){
            @Override
            public void onSlotChanged(){
                tile_entity.input_invalid=false;
                tile_entity.output_valid=false;
            }
        });//input
        addSlot(new Slot(tile_entity, 1, 116,35) {
            @Override
            public boolean isItemValid(ItemStack stack){
                return false;
            }
            @Override
            public void onSlotChanged(){
                tile_entity.input_invalid=false;
                tile_entity.output_valid=false;
            }
        });//output
        addSlot(new Slot(tile_entity, 2, 56,53) {
            @Override
            public boolean isItemValid(ItemStack stack){
                return AbstractFurnaceTileEntity.isFuel(stack);
            }
        });//fuel
        
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        
    }

    public static ContainerType<BurnerGrinderContainer> createContainerType(){
        ContainerType<BurnerGrinderContainer> c = IForgeContainerType.create(BurnerGrinderContainer::new);
        c.setRegistryName(RedTechMod.makeResourceLocation("burner_grinder"));
        return c;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning(){
        return tile_entity.burnTime >0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        int workTime = tile_entity._getWorkTime();
        int craftTime = tile_entity._getCraftTime();
        return craftTime != 0 && workTime != 0 ? workTime * 24 / craftTime : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int maxBurnTime = tile_entity.maxBurnTime;
        if (maxBurnTime == 0) {
            maxBurnTime = 200;
        }

        return tile_entity.burnTime * 13 / maxBurnTime;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return tile_entity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slot) {
        ItemStack stack=ItemStack.EMPTY;
        Slot slot_data=inventorySlots.get(slot);
        if(slot_data!=null&&slot_data.getHasStack()){
            ItemStack original_stack=slot_data.getStack();
            stack=original_stack.copy();
            if(slot<3){//machine slot
                if(!mergeItemStack(original_stack,3,39,true)){
                    return ItemStack.EMPTY;
                }
            }else{//player slot
                if(!(AbstractFurnaceTileEntity.isFuel(original_stack)&&mergeItemStack(original_stack,2,3,false))){//try to merge fuel
                    if(tile_entity.getInput().isEmpty()){
                        GrindingRecipe recipe=tile_entity._getWorld().getRecipeManager().getRecipe(GrindingRecipe.TYPE,new Inventory(original_stack),tile_entity._getWorld()).orElse(null);
                        if(recipe==null){
                            return ItemStack.EMPTY;
                        }
                    }
                    if(!mergeItemStack(original_stack,0,1,false)) {//else try to merge input
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (original_stack.isEmpty()) {
                slot_data.putStack(ItemStack.EMPTY);
            } else {
                slot_data.onSlotChanged();
            }
            if (original_stack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot_data.onTake(player, original_stack);
        }
        return stack;
    }
}
