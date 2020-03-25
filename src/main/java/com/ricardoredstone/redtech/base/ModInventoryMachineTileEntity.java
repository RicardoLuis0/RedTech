package com.ricardoredstone.redtech.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
abstract public class ModInventoryMachineTileEntity extends ModCommonMachineTileEntity implements ISidedInventory, ModInventory {

    protected int[] SLOTS_UP;
    protected int[] SLOTS_DOWN;
    protected int[] SLOTS_NORTH;
    protected int[] SLOTS_SOUTH;
    protected int[] SLOTS_EAST;
    protected int[] SLOTS_WEST;

    protected LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

    protected ModInventoryMachineTileEntity(TileEntityType<?> tileEntityTypeIn){
        super(tileEntityTypeIn);
        init_slots();
    }

    protected abstract void init_slots();//initialize slots

    @Override
    public int[] getSlotsForFace(Direction side) {
        switch(side){
            case UP:
                return SLOTS_UP;
            case DOWN:
                return SLOTS_DOWN;
            case NORTH:
                return SLOTS_NORTH;
            case SOUTH:
                return SLOTS_SOUTH;
            case EAST:
                return SLOTS_EAST;
            case WEST:
                return SLOTS_WEST;
            default:
                throw new RuntimeException("invalid side");
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            switch(facing){
                case UP:
                    return handlers[0].cast();
                case DOWN:
                    return handlers[1].cast();
                case NORTH:
                    return handlers[2].cast();
                case SOUTH:
                    return handlers[3].cast();
                case EAST:
                    return handlers[4].cast();
                case WEST:
                    return handlers[5].cast();
            }
        }
        return super.getCapability(capability,facing);
    }
}
