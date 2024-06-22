package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Categories;
import io.smallrye.mutiny.Uni;
import java.util.*;

public interface GetAllCategoriesUseCase {

    Uni<List<Categories>> getAllCategories();
}
