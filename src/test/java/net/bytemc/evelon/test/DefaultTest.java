package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.properties.StartupProperties;

import java.util.ArrayList;
import java.util.UUID;

public class DefaultTest {

    public static final Repository<TestRepository> REPOSITORY = Repository.create(TestRepository.class, StartupProperties.syncAll());
    public static final TestRepository TEST_REPO = new TestRepository("HabsGleich", UUID.randomUUID(), new ArrayList<>());

}
