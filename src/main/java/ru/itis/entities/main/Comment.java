package ru.itis.entities.main;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private UUID comment_id;
    private UUID user_id;
    private UUID recipe_id;
    private String text;
    private String creatorName;
}
