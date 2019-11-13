package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModOreBlock;
import com.ricardoredstone.redtech.implementation.blocks.BurnerGrinder;
import net.minecraft.world.gen.placement.CountRangeConfig;

public final class ModBlocks {
    public final ModOreBlock ETTROITE_ORE = RedTechMod.REGISTRY.add(new ModOreBlock("ettroite_ore",8,new CountRangeConfig(10,0,0,48)));
    public final BurnerGrinder BURNER_GRINDER = RedTechMod.REGISTRY.add(new BurnerGrinder());
}
