package com.ricardoredstone.redtech.implementation.blocks;

import com.ricardoredstone.redtech.base.ModDirectionalBlockHorizontal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class BurnerGrinder extends ModDirectionalBlockHorizontal {
    public BurnerGrinder() {
        super("burner_grinder", Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).sound(SoundType.STONE));
        setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH).with(BlockStateProperties.LIT, Boolean.valueOf(false)));
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.LIT);
    }
}
