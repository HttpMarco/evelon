package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;
import org.junit.jupiter.api.Test;

public class H2SqlRepositoryDefaultTest {

    @Test
    public void handleDefaultTest() {
        var repository = RepositoryBuilder.of(SqlTestElement.class)
                .addAfter(H2SqlLayer.class)
                .build();



    }

}
