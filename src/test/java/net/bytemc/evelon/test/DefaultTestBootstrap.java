package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.repository.properties.StartupProperties;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.UUID;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        Debugger.setEnable(true);

        var repository = Repository.create(TestRepository.class, StartupProperties.syncAll());


        repository.query().create(new TestRepository("polo", UUID.randomUUID(), 10));
    }
}
