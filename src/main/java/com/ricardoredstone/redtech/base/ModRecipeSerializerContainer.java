package com.ricardoredstone.redtech.base;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;

public class ModRecipeSerializerContainer<T extends IRecipe<?>> implements ModObject {
    private final IRecipeSerializer<T> serializer;
    public ModRecipeSerializerContainer(IRecipeSerializer<T> serializer){
        this.serializer=serializer;
    }

    public IRecipeSerializer<T> getSerializer() {
        return serializer;
    }

    @Override
    public void registerRecipeSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().register(serializer);
    }
}
