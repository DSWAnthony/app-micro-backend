package com.app.exa.domain.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Favorite {

    private Long id;
    private User user;
    private Book book;
    private LocalDateTime date;
}
