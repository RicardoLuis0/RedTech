package com.ricardoredstone.redtech.implementation.recipes;

import com.google.gson.JsonObject;
import com.ricardoredstone.redtech.RedTechMod;
import com.ricardoredstone.redtech.implementation.blocks.machines.grinder.CommonGrinderTileEntity;
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
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GrindingRecipe implements IRecipe<IInventory> {
    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrindingRecipe> {
        {
            setRegistryName(RedTechMod.makeResourceLocation("grinding"));
        }
        @Override
        public GrindingRecipe read(ResourceLocation recipeId, JsonObject json) {//CookingRecipeSerializer used as reference
            RedTechMod.LOGGER.log(Level.DEBUG,"Registering grinding recipe '"+recipeId+"'");
            return new GrindingRecipe(recipeId,
                    JSONUtils.getString(json, "group", ""),
                    JSONUtils.getInt(json,"grindingtime",800),
                    RecipeHelper.getIngredient(json,"ingredient"),
                    ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"))
            );
        }

        @Nullable
        @Override
        public GrindingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String group=buffer.readString();
            int grindingTime=buffer.readInt();
            Ingredient ingredient=Ingredient.read(buffer);
            ItemStack result=buffer.readItemStack();
            return new GrindingRecipe(recipeId,group,grindingTime,ingredient,result);
        }

        @Override
        public void write(PacketBuffer buffer, GrindingRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeInt(recipe.grindingTime);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }
    }

    private static class Type implements IRecipeType<GrindingRecipe> {
        public String toString(){
            return RedTechMod.MOD_ID+":grinding";
        }
    }

    public static final IRecipeSerializer<GrindingRecipe> SERIALIZER = new Serializer();
    public static final IRecipeType<GrindingRecipe> TYPE = new Type();

    private final ResourceLocation id;
    private final String group;
    private final Ingredient ingredient;
    private final ItemStack result;

    private final int grindingTime;

    GrindingRecipe(ResourceLocation id, String group, int grindingTime, Ingredient ingredient, ItemStack result){
        this.id=id;
        this.ingredient=ingredient;
        this.result=result;
        this.group=group;
        this.grindingTime=grindingTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return ingredient.test(inv.getStackInSlot(0));
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
