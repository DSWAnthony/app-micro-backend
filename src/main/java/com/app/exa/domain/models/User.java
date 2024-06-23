package com.app.exa.domain.models;

import lombok.Data;
import java.util.*;
@Data
public class User {

    private Long id;
    private String names;
    private String lastName;
    private String email;
    private String user;
    private String password;
    private List<Favorite> favorites;
}
