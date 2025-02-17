package com.cookshare.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private Blob image;
    private String downloadUrl;

    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
