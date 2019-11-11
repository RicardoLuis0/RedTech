package com.ricardoredstone.redtech.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ModDirectionalBlock extends ModSimpleBlock {//directional block (ex. log)
    public final boolean reverse;

    public ModDirectionalBlock(String name, Properties block_properties) {
        super(name, block_properties);
        this.reverse=false;
    }

    public ModDirectionalBlock(String name, Properties block_properties, boolean reverse) {
        super(name, block_properties);
        this.reverse=reverse;
    }

    public ModDirectionalBlock(String name, Properties block_properties, Item.Properties item_properties) {
        super(name, block_properties, item_properties);
        this.reverse=false;
    }

    public ModDirectionalBlock(String name, Properties block_properties, Item.Properties item_properties, boolean reverse) {
        super(name, block_properties, item_properties);
        this.reverse=reverse;
    }

    protected Direction getFacing(BlockPos pos, LivingEntity entity) {
        Direction dir=Direction.getFacingFromVector((float) (entity.posX - pos.getX()), (float) (entity.posY - pos.getY()), (float) (entity.posZ - pos.getZ()));
        if(reverse){
            return dir.getOpposite();
        }
        return dir;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if(entity!=null) {
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacing(pos, entity)), 2);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block,BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

}
