package com.ricardoredstone.redtech.implementation.recipes;

import com.google.gson.JsonObject;
import com.ricardoredstone.redtech.util.RecipeHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GrinderRecipe implements IRecipe<IInventory> {
    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrinderRecipe> {

        @Override
        public GrinderRecipe read(ResourceLocation recipeId, JsonObject json) {//CookingRecipeSerializer used as reference
            return new GrinderRecipe(recipeId,
                    JSONUtils.getString(json, "group", ""),
                    JSONUtils.getInt(json,"grindingtime",800),
                    RecipeHelper.getIngredient(json,"ingredient"),
                    ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"))
            );
        }

        @Nullable
        @Override
        public GrinderRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String group=buffer.readString();
            int grindingTime=buffer.readInt();
            Ingredient ingredient=Ingredient.read(buffer);
            ItemStack result=buffer.readItemStack();
            return new GrinderRecipe(recipeId,group,grindingTime,ingredient,result);
        }

        @Override
        public void write(PacketBuffer buffer, GrinderRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeInt(recipe.grindingTime);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
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
    private final ItemStack result;

    private final int grindingTime;

    GrinderRecipe(ResourceLocation id,String group,int grindingTime,Ingredient ingredient,ItemStack result){
        this.id=id;
        this.ingredient=ingredient;
        this.result=result;
        this.group=group;
        this.grindingTime=grindingTime;
    }
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        throw new RuntimeException("GrinderRecipe::matches unimplemented");//TODO
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
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

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getGrindingTime() {
        return grindingTime;
    }
}
