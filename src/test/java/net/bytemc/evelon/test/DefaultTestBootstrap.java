package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.DatabaseDebugger;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        DatabaseDebugger.setEnable(true);

        var repository = Repository.create(TestRepository.class);

        //repository.query().create(new TestRepository("polo", new Date()));

        for (TestRepository testRepository : repository.query().database().findAll()) {
            System.out.println(testRepository.getDate().toString());
        }

    }
}
