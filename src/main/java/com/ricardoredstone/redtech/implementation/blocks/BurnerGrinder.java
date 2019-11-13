package com.ricardoredstone.redtech.implementation.blocks;

import com.ricardoredstone.redtech.base.ModDirectionalBlockHorizontal;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BurnerGrinder extends ModDirectionalBlockHorizontal {
    public BurnerGrinder() {
        super("burner_grinder", Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).sound(SoundType.STONE));
    }
}
