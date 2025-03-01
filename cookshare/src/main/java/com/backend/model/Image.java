package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob //(Large Object) anotasyonu, büyük veri türlerini (örneğin büyük metinler veya binary dosyalar) veritabanında saklamak için kullanılır.
    private Blob image;
    private String downloadUrl;
    @OneToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
