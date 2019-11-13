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

    private final int count;
    private final CountRangeConfig spawn_range;
    private final OreFeatureConfig.FillerBlockType target;

    public ModOreBlock(String name,int count,CountRangeConfig spawn_range){
        this(name,count,spawn_range,false);
    }
    public ModOreBlock(String name,int count,CountRangeConfig spawn_range,boolean nether) {
        super(name, Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F,3.0F));
        this.count=count;
        this.spawn_range=spawn_range;
        this.target=nether?OreFeatureConfig.FillerBlockType.NETHERRACK:OreFeatureConfig.FillerBlockType.NATURAL_STONE;
    }

    @Override
    public void setupCommon(final FMLCommonSetupEvent event) {
        for(Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(target, getDefaultState(), count), Placement.COUNT_RANGE, spawn_range));
        }
    }
}
