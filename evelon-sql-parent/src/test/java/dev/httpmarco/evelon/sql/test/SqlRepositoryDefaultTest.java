package dev.httpmarco.evelon.sql.test;

import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.sql.parent.layer.SqlParentConnectionLayer;
import org.junit.jupiter.api.Test;

public class SqlRepositoryDefaultTest {

    @Test
    public void handleDefaultTest() {
        var repository = RepositoryBuilder.of(SqlTestElement.class)
                .addAfter(SqlParentConnectionLayer.class)
                .build();



    }

}
