package ru.itis.entities.main;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Community {
    private UUID community_id;
    private String name;
//    private List<User> users = new ArrayList<>();
    private String description;
    private byte[] image;
    private String imageForSrc;
    private Integer usersNumber;
}
