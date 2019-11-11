package com.ricardoredstone.redtech.base;

import com.ricardoredstone.redtech.RedTechMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreBlock extends ModSimpleBlock {

    final int count;
    final CountRangeConfig spawn_range;

    public ModOreBlock(String name,int count,CountRangeConfig spawn_range) {
        super(name, Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F,3.0F));
        this.count=count;
        this.spawn_range=spawn_range;
    }

    @Override
    public void setupCommon(final FMLCommonSetupEvent event) {
        for(Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, RedTechMod.BLOCKS.ETTROITE_ORE.getDefaultState(), count), Placement.COUNT_RANGE, spawn_range));
        }
    }
}
