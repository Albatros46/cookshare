package com.cookshare.service.impl;

import com.cookshare.model.Recipe;
import com.cookshare.model.User;
import com.cookshare.repository.RecipeRepository;
import com.cookshare.repository.UserRepository;
import com.cookshare.request.CreateRecipeRequest;
import com.cookshare.request.RecipeUpdateRequest;
import com.cookshare.service.interfaces.IRecipeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeService implements IRecipeService {

    private final RecipeRepository repository;
    private final UserRepository repoUser;
    @Override
    public Recipe createRecipe(CreateRecipeRequest request) {
        if(request==null || request.getUser()==null){
            throw new IllegalArgumentException("Invalid request!");
        }
        User user= Optional.ofNullable(repoUser.findByUsername(request.getUser().getUsername()))
                .map(existingUser->{
                    existingUser.getRecipe().add(request.getRecipe());
                    return existingUser;
                }).orElseGet(()->{
                    User newUser=new User(request.getUser().getUsername());
                    repoUser.save(newUser);
                    return newUser;
                });
        Recipe recipe=IRecipeService.createRecipe(request,user);

        return repository.save(recipe);
    }
    @Override
    public Recipe updateRecipe(RecipeUpdateRequest request, Long recipeId) {
        return null;
    }
    @Override
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return repository.findById(id).orElseThrow(()->new EntityNotFoundException("Recipe not found!"));
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        repository.deleteById(recipeId);
    }

    @Override
    public Set<String> getAllRecipeByCategories() {
        return repository.findAll()
                .stream()
                .map(Recipe::getCategory)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllRecipeCuisine() {
        return repository.findAll()
                .stream()
                .map(Recipe::getCuisine)
                .collect(Collectors.toSet());
    }
}
