package net.bytemc.evelon.test.mariadb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.test.DefaultTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import java.util.UUID;

public class MariaDBTest {

    @Test
    @Disabled
    public void test() {
        Debugger.setEnable(true);
        Evelon.setCradinates(DatabaseProtocol.MARIADB, "127.0.0.1", "", "root", "tnw", 3306);
        DefaultTest.REPOSITORY.query().create(DefaultTest.TEST_REPO);
    }
}
