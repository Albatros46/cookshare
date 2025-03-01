package com.backend.controller;

import com.backend.model.Recipe;
import com.backend.request.CreateRecipeRequest;
import com.backend.request.RecipeUpdateRequest;
import com.backend.service.interfaces.IRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {
    private final IRecipeService recipeService;

    @PostMapping("/add-recipe")
    public ResponseEntity<Recipe> createRecipe(CreateRecipeRequest request){
        return ResponseEntity.status(200).body(recipeService.createRecipe(request));
    }
    @GetMapping("/")
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
    @GetMapping("/getById/{recipeId}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId){
        return ResponseEntity.ok(recipeService.getRecipeById(recipeId));
    }
    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(RecipeUpdateRequest request,@PathVariable Long recipeId){
        return ResponseEntity.status(200).body(recipeService.updateRecipe(request,recipeId));
    }
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId){
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getAllCategories(){
        return ResponseEntity.ok(recipeService.getAllRecipeCategories());
    }
    @GetMapping("/cuisines")
    public ResponseEntity<Set<String>> getAllCuisines(){
        return ResponseEntity.ok(recipeService.getAllRecipeCuisine());
    }
}
