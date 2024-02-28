package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.SimpleListTestRepository;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;

public final class H2SimpleListRepositoryTest {

    public static void main(String[] args) {
        var repository = RepositoryBuilder.of(SimpleListTestRepository.class).addAfter(H2SqlLayer.class).build();


    }
}
