package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.DatabaseDebugger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        DatabaseDebugger.setEnable(true);

        var repository = Repository.create(TestRepository.class);

        var names = new ArrayList<NameEntry>();
        names.add(new NameEntry("Polo", 921309120000003L));
        names.add(new NameEntry("imLisaaa", 921309323120000003L));
        names.add(new NameEntry("menschentoaster", 921309120000003L));
        names.add(new NameEntry("GamePvG", 921309323120000003L));
        repository.query().create(new TestRepository("polo", 99, names));

        for (TestRepository testRepository : repository.query().database().findAll()) {
            for (NameEntry username : testRepository.getUsernames()) {
                System.out.println(username.getTimestamp() + ":" + username.getName());
            }
        }
    }
}
