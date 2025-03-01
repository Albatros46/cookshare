package com.backend.request;

import com.backend.model.Recipe;
import com.backend.model.User;
import lombok.Data;

@Data
public class CreateRecipeRequest {
    private Recipe recipe;
    private User user;
}
