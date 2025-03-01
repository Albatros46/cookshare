package com.backend.controller;

import com.backend.model.Recipe;
import com.backend.request.CreateRecipeRequest;
import com.backend.service.interfaces.IRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {
    private final IRecipeService recipeService;

    public ResponseEntity<Recipe> createRecipe(CreateRecipeRequest request){
        return ResponseEntity.status(200).body(recipeService.createRecipe(request));
    }
}
