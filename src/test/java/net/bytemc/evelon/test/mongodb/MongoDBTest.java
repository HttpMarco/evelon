package net.bytemc.evelon.test.mongodb;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.repository.Filter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static net.bytemc.evelon.test.DefaultTest.*;

public class MongoDBTest {

    @Test
    @Disabled
    public void test() {
        Debugger.setEnable(true);
        Evelon.setCradinates(DatabaseProtocol.MONGODB, "localhost", "habi123", "root", "admin", 27017);

        REPOSITORY.query().createIfNotExists(TEST_REPO);

        var query = REPOSITORY.query().filter(Filter.match("name", "HabsGleich")).database().findFirst();
        System.out.println(query);

        /*for (TestRepository repo : REPOSITORY.query().database().findAll()) {
            System.out.println(repo.toString());
        }*/
    }

}
