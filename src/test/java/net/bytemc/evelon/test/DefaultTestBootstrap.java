package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.Debugger;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.UUID;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        Debugger.setEnable(true);

        var repository = Repository.create(TestRepository.class);

        var map = new HashMap<String, Long>();

        map.put("lobby-1", System.currentTimeMillis());
        map.put("lobby-2", System.currentTimeMillis());

        repository.query().create(new TestRepository("polo", UUID.randomUUID(), map, 10));
    }
}
