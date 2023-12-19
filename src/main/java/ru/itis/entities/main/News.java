package ru.itis.entities.main;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News implements Serializable {
    private UUID news_id;
    private UUID user_id;
    private String name;
    private String text;
    private byte[] image;
    private String imageForSrc;
}
