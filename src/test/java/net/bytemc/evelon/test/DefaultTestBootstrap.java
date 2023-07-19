package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Filters;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.DatabaseDebugger;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        DatabaseDebugger.setEnable(true);

        var repository = Repository.create(TestRepository.class);

        for (TestRepository testRepository : repository.query().filter(Filters.match("coins", 10)).database().findAll()) {
            repository.getName();
        }
    }
}
