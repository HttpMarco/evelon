package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.DatabaseDebugger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        DatabaseDebugger.setEnable(false);

        var repository = Repository.create(TestRepository.class);

        var names = new ArrayList<String>();
        names.add("ibimsBuilder");
        names.add("Golgolex");
        names.add("SpigotPlugins");

      //  repository.query().create(new TestRepository("polo", 99, names));

        for (TestRepository testRepository : repository.query().database().findAll()) {
            for (String username : testRepository.getUsernames()) {
                System.out.println(username);
            }
        }



    }
}
