package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.SimpleTestRepository;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;

public class H2SimpleRepositoryTest {

    public static void main(String[] args) {
        var repository = RepositoryBuilder.of(SimpleTestRepository.class).addAfter(H2SqlLayer.class).build();


    }
}
