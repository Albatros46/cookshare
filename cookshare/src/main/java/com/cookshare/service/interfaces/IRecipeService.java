package com.cookshare.service.interfaces;

import com.cookshare.model.Recipe;
import com.cookshare.model.User;
import com.cookshare.request.CreateRecipeRequest;
import com.cookshare.request.UpdateRecipeRequest;

import java.util.List;
import java.util.Set;

public interface IRecipeService {
     Recipe createRecipe(CreateRecipeRequest request);
     List<Recipe> getAllRecipes();
     Recipe getRecipeById(Long id);
     Recipe updateRecipe(UpdateRecipeRequest request, Long recipeId);
     void deleteRecipe(Long recipeId);
     Set<String> getAllRecipeByCategories();
     Set<String> getAllRecipeCuisine();

     static Recipe createRecipe(CreateRecipeRequest request, User user){
         Recipe recipe=new Recipe();
         Recipe createRequest=request.getRecipe();
         recipe.setTitle(createRequest.getTitle());
         recipe.setCuisine(createRequest.getCuisine());
         recipe.setCategory(createRequest.getCategory());
         recipe.setDescription(createRequest.getDescription());
         recipe.setCookTime(createRequest.getCookTime());
         recipe.setPrepTime(createRequest.getPrepTime());
         recipe.setInstruction(createRequest.getInstruction());
         recipe.setIngredients(createRequest.getIngredients());
         recipe.setUser(user);
         return recipe;
     }

    static Recipe updateRecipe(Recipe existingRecipe,UpdateRecipeRequest updateRequest){
        existingRecipe.setTitle(updateRequest.getTitle());
        existingRecipe.setDescription(updateRequest.getDescription());
        existingRecipe.setInstruction(updateRequest.getInstruction());
        existingRecipe.setCuisine(updateRequest.getCuisine());
        existingRecipe.setPrepTime(updateRequest.getPrepTime());
        existingRecipe.setCategory(updateRequest.getCategory());
        existingRecipe.setCookTime(updateRequest.getCookTime());
        existingRecipe.setIngredients(updateRequest.getIngredients());
        return existingRecipe;
    }
}
