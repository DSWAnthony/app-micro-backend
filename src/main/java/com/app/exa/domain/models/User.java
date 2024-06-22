package com.app.exa.domain.models;

import lombok.Data;
import java.util.*;
@Data
public class User {

    private Long id;
    private String names;
    private String email;
    private String password;
    private List<Favorite> favorites;
}
