package com.ricardoredstone.redtech.base;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ModSimpleItem extends Item implements ModObject {

    public ModSimpleItem(String name, Properties properties) {
        super(properties);
        setRegistryName(ModObject.makeResourceLocation(name));
    }

    @Override
    public void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(this);
    }

}
