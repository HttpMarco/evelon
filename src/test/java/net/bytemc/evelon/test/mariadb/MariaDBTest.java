package net.bytemc.evelon.test.mariadb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.properties.StartupProperties;
import net.bytemc.evelon.test.TestRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MariaDBTest {

    private static final TestRepository TEST_MARIADB_REPOSITORY = new TestRepository(
        "HabsGleich",
        UUID.fromString("013eddfc-e9f7-46b3-a52c-a8cfac27d64e"),
        64
    );

    @Test
    public void test() {
        Debugger.setEnable(true);
        Evelon.setDatabaseCradinates(new DatabaseCradinates(
            DatabaseProtocol.MARIADB,
            "127.0.0.1",
            "test123",
            "root",
            "tnw",
            3306
        ));

        final Repository<TestRepository> repository = Repository.create(TestRepository.class, StartupProperties.syncAll());
        repository.query().create(TEST_MARIADB_REPOSITORY);
    }

}
