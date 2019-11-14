package com.ricardoredstone.redtech.implementation.recipes;

import com.google.gson.JsonObject;
import com.ricardoredstone.redtech.RedTechMod;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class GrinderRecipe implements IRecipe<IInventory> {
    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrinderRecipe> {

        @Override
        public GrinderRecipe read(ResourceLocation recipeId, JsonObject json) {
            throw new RuntimeException("GrinderRecipe::Serializer::read unimplemented");
        }

        @Nullable
        @Override
        public GrinderRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            throw new RuntimeException("GrinderRecipe::Serializer::read unimplemented");
        }

        @Override
        public void write(PacketBuffer buffer, GrinderRecipe recipe) {
            throw new RuntimeException("GrinderRecipe::Serializer::write unimplemented");
        }
    }

    private static class GrinderRecipeType implements IRecipeType<GrinderRecipe> {
        public String toString(){
            return "grinder";
        }
    }

    public static final IRecipeSerializer<GrinderRecipe> SERIALIZER = new Serializer();
    public static final IRecipeType<GrinderRecipe> TYPE = new GrinderRecipeType();
    public final ResourceLocation id;
    public final String group;
    private final Ingredient ingredient;
    private final ItemStack output;

    GrinderRecipe(String name,String group,Ingredient ingredient,ItemStack output){
        id=RedTechMod.makeResourceLocation(name);
        this.ingredient=ingredient;
        this.output=output;
        this.group=group;
    }
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        throw new RuntimeException("GrinderRecipe::matches unimplemented");//TODO
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> l = NonNullList.create();
        l.add(ingredient);
        return l;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return TYPE;
    }
}
