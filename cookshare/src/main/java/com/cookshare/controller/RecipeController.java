package com.cookshare.controller;

import com.cookshare.model.Recipe;
import com.cookshare.request.CreateRecipeRequest;
import com.cookshare.request.UpdateRecipeRequest;
import com.cookshare.service.interfaces.IRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final IRecipeService recipeService;
/*
    @PostMapping("/addRecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody CreateRecipeRequest request){
        System.out.println("The request ===============================:"+request.getUser()+"###############"+request);
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }*/
    @PostMapping("/addRecipe")
    public ResponseEntity<?> createRecipe(@RequestBody CreateRecipeRequest request){
        if (request == null || request.getRecipe() == null || request.getUser() == null) {
            return ResponseEntity.badRequest().body("Invalid request: Recipe and User fields cannot be null!");
        }

        System.out.println("Request received: " + request);
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }
    @GetMapping("/getAllRecipe") //allRecipe
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/recipeById/{recipeId}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId){
        return ResponseEntity.ok(recipeService.getRecipeById(recipeId));
    }

    @PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(UpdateRecipeRequest request,@PathVariable Long recipeId){
     //   return ResponseEntity.status(200).body(recipeService.updateRecipe(request,recipeId));
        return ResponseEntity.ok(recipeService.updateRecipe(request,recipeId)); //diger yöntem
    }

    @DeleteMapping("/deleteRecipe/{recipeId}")
    public ResponseEntity<Recipe> removeRecipe(@PathVariable Long recipeId){
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getAllCategories(){
        return ResponseEntity.ok(recipeService.getAllRecipeByCategories());
    }

    @GetMapping("/cuisines")
    public ResponseEntity<Set<String>> getAllCuisines(){
        return ResponseEntity.ok(recipeService.getAllRecipeCuisine());
    }
}
