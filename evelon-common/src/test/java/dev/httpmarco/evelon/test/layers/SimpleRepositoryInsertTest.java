package dev.httpmarco.evelon.test.layers;

import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.test.TestRepository;
import org.junit.jupiter.api.Test;

public class SimpleRepositoryInsertTest {

    @Test
    public void handle() {

        Repository<TestRepository> repository = RepositoryBuilder.of(TestRepository.class)
                .withLocalStorage()
                .build();







    }
}
