package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;

public interface QueryMethodInfo {

    Repository<?> associatedRepository();

}
