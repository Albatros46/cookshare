package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    //------------------------
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//ManyToOne

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    private List<Review> reviews;//OneToMany

    @OneToOne(mappedBy = "recipe",cascade = CascadeType.ALL)
    private Image image;//OneToOne
}
