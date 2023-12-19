package ru.itis.entities.main;

import lombok.*;

import java.util.Arrays;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID user_id;
    private String name;
    private Double rating;
    private String email;
    private String password;
    private String information;
    private String role;
    private Integer recipeNumber;
    private byte[] image;
    private UUID community_id;
    private String imageForSrc;
    private Integer ratingCount;
}
