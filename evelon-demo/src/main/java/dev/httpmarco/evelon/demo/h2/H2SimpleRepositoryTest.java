package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.SimpleTestRepository;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;

public final class H2SimpleRepositoryTest {

    public static void main(String[] args) {
        var repository = RepositoryBuilder.of(SimpleTestRepository.class).addAfter(H2SqlLayer.class).build();

        // CREATE VALUE
        repository.query().create(new SimpleTestRepository());

    }
}
