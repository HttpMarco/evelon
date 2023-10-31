package net.bytemc.evelon.test.mariadb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.properties.StartupProperties;
import net.bytemc.evelon.test.TestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MariaDBTest {

    @Test
    @Disabled
    public void test() {
        Debugger.setEnable(true);
        Evelon.setCradinates(DatabaseProtocol.MARIADB, "127.0.0.1", "test123", "root", "tnw", 3306);
        TestRepository.REPOSITORY.query().create(TestRepository.TEST_REPO);
    }
}
