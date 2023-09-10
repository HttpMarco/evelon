package net.bytemc.evelon.test.mongodb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.test.TestRepository;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created: 07.09.2023
 *
 * @author HabsGleich
 */
public class MongoDBTest {

    private static final TestRepository[] REPOSITORIES = new TestRepository[] {
            new TestRepository(
                    "HabsGleich",
                    UUID.fromString("013eddfc-e9f7-46b3-a52c-a8cfac27d64e"),
                    new HashMap<>()
            ),
            new TestRepository(
                    "Habi",
                    UUID.fromString("c09a363f-9b04-4343-b4e0-380e286bd751"),
                    new HashMap<>(Map.of("Lobby-1", 52000L, "BedWars-1", 23000L))
            )
    };

    @Test
    public void test() {
        Debugger.setEnable(true);
        System.out.println(UUID.randomUUID());
        Evelon.setDatabaseCradinates(new DatabaseCradinates(
            DatabaseProtocol.MONGODB,
            "127.0.0.1",
            "",
            "admin",
            "admin",
            27017
        ));

        final Repository<TestRepository> repository = Repository.create(TestRepository.class);
        for (TestRepository testRepository : REPOSITORIES) {
            testRepository.getConnectedStates().put("Test", 1L);
            repository.query().database().update(testRepository);
        }

        for (TestRepository repo : repository.query().database().findAll()) {
            System.out.println(repo.toString());
        }
    }

}
