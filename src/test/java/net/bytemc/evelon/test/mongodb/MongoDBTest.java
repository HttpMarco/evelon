package net.bytemc.evelon.test.mongodb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Filter;
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

    private static final TestRepository[] REPOSITORIES = new TestRepository[]{
            new TestRepository(
                    "HabsGleich",
                    UUID.fromString("013eddfc-e9f7-46b3-a52c-a8cfac27d64e"),
                    30
            ),
            new TestRepository(
                    "Habi",
                    UUID.fromString("c09a363f-9b04-4343-b4e0-380e286bd751"),
                    64
            )
    };

    @Test
    public void test() {
        Debugger.setEnable(true);
        Evelon.setDatabaseCradinates(new DatabaseCradinates(
                DatabaseProtocol.MONGODB,
                "localhost",
                "ThisIsAGoodPasswordTrustMe",
                "admin",
                "admin",
                27017
        ));

        final Repository<TestRepository> repository = Repository.create(TestRepository.class);
        for (TestRepository testRepository : REPOSITORIES) {
            repository.query().createIfNotExists(testRepository);
        }

        for (TestRepository repo : repository.query().filter(Filter.between("personalNumber", 25, 36)).database().findAll()) {
            System.out.println(repo.toString());
        }
    }

}
