package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Favorite;
import io.smallrye.mutiny.Uni;
import java.util.*;

public interface GetFavoriteByUserIdUseCase {
    Uni<List<Favorite>> getFavoriteByUserId(Long id);
}
