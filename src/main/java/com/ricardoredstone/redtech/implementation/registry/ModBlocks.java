package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModOreBlock;
import com.ricardoredstone.redtech.base.ModSimpleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class ModBlocks {
    public final ModSimpleBlock SHINEROCK_BLOCK = (ModSimpleBlock) RedTechMod.MOD_REGISTRY.addObject(new ModSimpleBlock("shinerock_block", Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(0.3F).lightValue(12)));
    public final ModOreBlock ETTROITE_ORE = (ModOreBlock) RedTechMod.MOD_REGISTRY.addObject(new ModOreBlock("ettroite_ore",9,new CountRangeConfig(10,0,0,48)));
}
