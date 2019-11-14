package com.ricardoredstone.redtech.implementation.blocks;

import com.ricardoredstone.redtech.base.ModDirectionalBlockHorizontal;
import com.ricardoredstone.redtech.base.ModMachine;
import com.ricardoredstone.redtech.implementation.blocks.tile_entities.BurnerGrinderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BurnerGrinder extends ModMachine {
    public BurnerGrinder() {
        super("burner_grinder", Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).sound(SoundType.STONE).lightValue(13).harvestTool(ToolType.PICKAXE).harvestLevel(0));
    }

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        //TODO
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new BurnerGrinderTileEntity();
    }
}
