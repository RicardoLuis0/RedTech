package com.ricardoredstone.redtech.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
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
        this(name, block_properties,true);
    }

    public ModDirectionalBlock(String name, Properties block_properties, boolean reverse) {
        super(name, block_properties);
        this.reverse=reverse;
    }

    public ModDirectionalBlock(String name, Properties block_properties, Item.Properties item_properties) {
        this(name, block_properties, item_properties,true);
    }

    public ModDirectionalBlock(String name, Properties block_properties, Item.Properties item_properties, boolean reverse) {
        super(name, block_properties, item_properties);
        this.reverse=reverse;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(BlockStateProperties.FACING, reverse?context.getNearestLookingDirection().getOpposite():context.getNearestLookingDirection());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block,BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

}
