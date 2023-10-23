package net.bytemc.evelon.test.mysql;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.properties.StartupProperties;
import net.bytemc.evelon.test.TestRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public final class MySQLDBTest {

    @Test
    public void test() {
        Debugger.setEnable(true);
        Evelon.setDatabaseCradinates(new DatabaseCradinates(
                DatabaseProtocol.MYSQL,
                "localhost",
                "",
                "root",
                "polo",
                3306
        ));

        final Repository<TestRepository> repository = Repository.create(TestRepository.class, StartupProperties.syncAll());
        repository.query().create(new TestRepository("polo", UUID.randomUUID(), 20));
    }

}
