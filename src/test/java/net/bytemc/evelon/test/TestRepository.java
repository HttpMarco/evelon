package net.bytemc.evelon.test;

import lombok.*;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.repository.properties.StartupProperties;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "polus2")
public class TestRepository {

    public static final Repository<TestRepository> REPOSITORY = Repository.create(TestRepository.class, StartupProperties.syncAll());
    public static final TestRepository TEST_REPO = new TestRepository("HabsGleich", UUID.randomUUID(), 64);

    @PrimaryKey
    private String name;
    private UUID uniqueId;
    private int personalNumber;

}
