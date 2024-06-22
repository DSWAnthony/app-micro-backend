package com.app.exa.app.ports.usecases;


import com.app.exa.app.ports.in.UpdateBookUseCase;
import com.app.exa.app.ports.out.BookRepositoryPort;
import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateBookByIdUseCaseImpl implements UpdateBookUseCase {
    @Inject
    BookRepositoryPort bookRepositoryPort;

    @Override
    public Uni<Book> updateBook(Book book,Long categorie_id) {
        return bookRepositoryPort.updateBook(book,categorie_id);
    }
}
