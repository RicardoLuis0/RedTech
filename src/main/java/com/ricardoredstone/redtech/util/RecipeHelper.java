package com.ricardoredstone.redtech.util;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JSONUtils;

public final class RecipeHelper {
    public static Ingredient getIngredient(JsonObject json, String name){
        if (!json.has(name)){
            throw new com.google.gson.JsonSyntaxException("Missing "+name);
        } else if (!json.get("result").isJsonObject()){
            throw new com.google.gson.JsonSyntaxException("Invalid "+name);
        }
        return Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
    }
}
