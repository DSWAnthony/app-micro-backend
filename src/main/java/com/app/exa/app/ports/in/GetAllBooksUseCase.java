package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;
import java.util.*;

public interface GetAllBooksUseCase {

    Uni<List<Book>> getAllBooks();
}
