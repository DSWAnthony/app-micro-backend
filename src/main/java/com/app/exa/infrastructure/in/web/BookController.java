package com.app.exa.infrastructure.in.web;

import com.app.exa.app.ports.in.*;
import com.app.exa.domain.models.Book;
import com.app.exa.domain.models.Respon;
import io.quarkus.security.PermissionsAllowed;
import io.smallrye.mutiny.Uni;
import java.util.*;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import static io.vertx.sqlclient.impl.SocketConnectionBase.logger;

@ApplicationScoped
@GraphQLApi
public class BookController {
    @Inject
    CreateBookUseCase createBookUseCase;
    @Inject
    GetAllBooksUseCase getAllBooksUseCase;
    @Inject
    GetBookByIdUseCase getBookByIdUseCase;
    @Inject
    DeleteBookByIdUseCase deleteBookByIdUseCase;
    @Inject
    UpdateBookUseCase updateBookUseCase;

    @RolesAllowed({"user","admin"})
    @Query("GetAllBooks")
    public Uni<List<Book>> getAllBooks(){
        logger.debug("Executing getAllBooks() method.");
        return getAllBooksUseCase.getAllBooks();
    }

    @RolesAllowed({"admin","user"})
    @Query("GetBookById")
    public Uni<Book> getBookById(Long id){
        return getBookByIdUseCase.getBookById(id);
    }

    @RolesAllowed({"admin","user"})
    @Mutation("CreateBook")
    public Uni<Book> createBook(Book book,Long categorie_id){
        return createBookUseCase.createBook(book,categorie_id);
    }

    @RolesAllowed("admin")
    @Mutation("DeleteBookByID")
    public Uni<Respon> deleteBookById(Long id){ return deleteBookByIdUseCase.deleteBookById(id);}

    @RolesAllowed("admin")
    @Mutation("UpdateBook")
    public Uni<Book> updateBook(Book book,Long categorie_id){
        return updateBookUseCase.updateBook(book,categorie_id);
    }
}
