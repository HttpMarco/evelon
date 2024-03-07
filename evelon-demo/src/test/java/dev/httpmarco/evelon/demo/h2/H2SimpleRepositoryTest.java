package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.query.response.ResponseType;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.SimpleTestRepository;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class H2SimpleRepositoryTest {

    private final Repository<SimpleTestRepository> repository = RepositoryBuilder.of(SimpleTestRepository.class)
            .addAfter(H2SqlLayer.class)
            .build();

    @Test
    void creation() {
        assertEquals(ResponseType.SUCCESS, repository.query().create(new SimpleTestRepository(1, 200)).response());
    }
}
