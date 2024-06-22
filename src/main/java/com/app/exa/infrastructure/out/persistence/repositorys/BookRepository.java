package com.app.exa.infrastructure.out.persistence.repositorys;

import com.app.exa.domain.entities.BookEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<BookEntity,Long> {
}
