package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.junit.jupiter.api.Test;

public class RepositoryBuilderTest {

    @Test
    public void create() {
        var repository = Repository.build(SimpleModel.class).withId("players").withLayer(H2Layer.class).build();

    }

}
