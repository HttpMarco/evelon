package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.repository.Repository;
import org.junit.jupiter.api.Test;

public class RepositoryBuilderTest {

    @Test
    public void create() {

        System.out.println(!SimpleModel.class.isSynthetic());

        Repository<SimpleModel> repository = Repository.build(SimpleModel.class).withId("players").build();

    }

}
