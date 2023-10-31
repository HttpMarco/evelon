package net.bytemc.evelon.test.mongodb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.annotations.Ignore;
import net.bytemc.evelon.test.TestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MongoDBTest {

    @Test
    @Disabled
    public void test() {
        Debugger.setEnable(true);
        Evelon.setCradinates(DatabaseProtocol.MONGODB, "localhost", "ThisIsAGoodPasswordTrustMe", "admin", "admin", 27017);

        for (TestRepository repo : TestRepository.REPOSITORY.query().filter(Filter.between("personalNumber", 25, 36)).database().findAll()) {
            System.out.println(repo.toString());
        }
    }
}
