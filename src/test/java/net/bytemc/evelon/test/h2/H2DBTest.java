package net.bytemc.evelon.test.h2;

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
public class H2DBTest {

    private static final TestRepository TEST_MONGO_REPOSITORY = new TestRepository(
        "HabsGleich",
        UUID.fromString("013eddfc-e9f7-46b3-a52c-a8cfac27d64e"),
        new HashMap<>(Map.of())
    );

    @Test
    public void test() {
        Debugger.setEnable(true);
        Evelon.setDatabaseCradinates(new DatabaseCradinates(
            DatabaseProtocol.H2,
            "127.0.0.1",
            "",
            "",
            "tnw",
            27017
        ));

        final Repository<TestRepository> repository = Repository.create(TestRepository.class);
        repository.query().database().update(TEST_MONGO_REPOSITORY);
    }

}
