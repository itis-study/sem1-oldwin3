package ru.itis.entities.main;

import lombok.*;

import java.util.Arrays;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private UUID recipe_id;
    private String name;
    private Double rating;
    private Double ratingCount;
    private String description;
    private byte[] image;
    private String ingredients;
    private String stages;
    private UUID user_id;
    private String imageForSrc;

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
