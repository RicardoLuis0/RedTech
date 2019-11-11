package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModSimpleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItems {
    public final ModSimpleItem ETTROITE_DUST = (ModSimpleItem) RedTechMod.MOD_REGISTRY.addObject(new ModSimpleItem("ettroite_dust", new Item.Properties().group(ItemGroup.MATERIALS)));
    public final ModSimpleItem IRON_DUST = (ModSimpleItem) RedTechMod.MOD_REGISTRY.addObject(new ModSimpleItem("iron_dust", new Item.Properties().group(ItemGroup.MATERIALS)));
    public final ModSimpleItem GOLD_DUST = (ModSimpleItem) RedTechMod.MOD_REGISTRY.addObject(new ModSimpleItem("gold_dust", new Item.Properties().group(ItemGroup.MATERIALS)));

}
