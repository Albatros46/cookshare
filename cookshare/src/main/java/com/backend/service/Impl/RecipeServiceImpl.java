package com.backend.service.Impl;

import com.backend.model.Recipe;
import com.backend.model.User;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;
import com.backend.request.CreateRecipeRequest;
import com.backend.request.RecipeUpdateRequest;
import com.backend.service.interfaces.IRecipeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements IRecipeService {
    private final RecipeRepository repository;
    private final UserRepository userRepository;
    @Override
    public Recipe createRecipe(CreateRecipeRequest request) {
        if (request == null || request.getUser()== null){
            throw new IllegalArgumentException("Invalid request");
        }
        User user= Optional.ofNullable(userRepository.findByUsername(request.getUser().getUsername()))
                .map(existingUser->{
                    existingUser.getRecipe().add(request.getRecipe());
                    return existingUser;
                }).orElseGet(()->{
                    User newUser=new User(request.getUser().getUsername());
                    userRepository.save(newUser);
                    return newUser;
                });
        Recipe recipe = IRecipeService.createRecipe(request,user);
        return repository.save(recipe);
    }
    @Override
    public Recipe updateRecipe(RecipeUpdateRequest request, Long recipeId) {
        Recipe recipe=getRecipeById(recipeId);
        Recipe theRecipe=IRecipeService.updateRecipe(recipe,request);
        return repository.save(theRecipe);
    }
    @Override
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Recipe not found!"));
    }
    @Override
    public void deleteRecipe(Long recipeId) {
        repository.deleteById(recipeId);
    }

    @Override
    public Set<String> getAllRecipeCategories() {
        return repository.findAll().stream().map(Recipe::getCategory).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllRecipeCuisine() {
        return repository.findAll().stream().map(Recipe::getCuisine).collect(Collectors.toSet());
    }
}
