package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModOreBlock;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class ModBlocks {
    public final ModOreBlock ETTROITE_ORE = (ModOreBlock) RedTechMod.MOD_REGISTRY.addObject(new ModOreBlock("ettroite_ore",8,new CountRangeConfig(10,0,0,48)));
}
