package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModOreBlock;
import com.ricardoredstone.redtech.implementation.blocks.machines.grinder.BurnerGrinder;
import net.minecraft.world.gen.placement.CountRangeConfig;

public final class ModBlocks {
    public ModOreBlock ETTROITE_ORE = RedTechMod.REGISTRY.add(
            new ModOreBlock("ettroite_ore",8, new CountRangeConfig(10,0,0,48), 1)
    );
    public BurnerGrinder BURNER_GRINDER = RedTechMod.REGISTRY.add(new BurnerGrinder());
}
