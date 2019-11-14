package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.base.ModRecipeSerializerContainer;
import com.ricardoredstone.redtech.implementation.recipes.GrindingRecipe;

public final class ModRecipeSerializers {
    public final ModRecipeSerializerContainer<GrindingRecipe> GRINDER_RECIPE = RedTechMod.REGISTRY.add(new ModRecipeSerializerContainer<>(GrindingRecipe.SERIALIZER, GrindingRecipe.TYPE));
}
