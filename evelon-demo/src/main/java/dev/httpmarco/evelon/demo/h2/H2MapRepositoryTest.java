package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.MapBothElementTestRepository;
import dev.httpmarco.evelon.demo.MapKeyObjectValueElementTestRepository;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;

public class H2MapRepositoryTest {

    public static void main(String[] args) {
      //  var repository = RepositoryBuilder.of(MapBothElementTestRepository.class).addAfter(H2SqlLayer.class).build();
        var repository = RepositoryBuilder.of(MapKeyObjectValueElementTestRepository.class).addAfter(H2SqlLayer.class).build();
    }

}
