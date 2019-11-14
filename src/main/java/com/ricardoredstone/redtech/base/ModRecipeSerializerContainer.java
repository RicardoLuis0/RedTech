package com.ricardoredstone.redtech.base;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;

public class ModRecipeSerializerContainer<T extends IRecipe<?>> implements ModObject {
    private final IRecipeSerializer<T> serializer;
    private final IRecipeType<T> type;
    public ModRecipeSerializerContainer(IRecipeSerializer<T> serializer, IRecipeType<T> type){
        this.serializer=serializer;
        this.type=type;
    }

    @Override
    public void registerRecipeSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE,type.toString(),type);
        event.getRegistry().register(serializer);
    }
}
