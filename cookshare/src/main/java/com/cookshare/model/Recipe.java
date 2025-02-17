package com.cookshare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String instruction;
    private String prepTime;
    private String cookTime;
    private String category;
    private String description;
    private String cuisine;
    private int likeCount;
    private double averageRating;
    private int totalRateCount;
    @ElementCollection
    @CollectionTable(name = "recipe_ingredients",joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recipe",cascade=CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "recipe", cascade=CascadeType.ALL)
    private Image image;
}
