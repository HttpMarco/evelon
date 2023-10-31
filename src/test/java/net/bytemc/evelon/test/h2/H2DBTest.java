package net.bytemc.evelon.test.h2;

import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.properties.StartupProperties;
import net.bytemc.evelon.test.DefaultTest;
import net.bytemc.evelon.test.TestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class H2DBTest {

    @Test
    @Disabled
    public void test() {
        Debugger.setEnable(true);
        Evelon.useDefaultLocalDatabase();
        final Repository<TestRepository> repository = Repository.create(TestRepository.class, StartupProperties.syncAll());
        repository.query().create(DefaultTest.TEST_REPO);
    }
}
