package net.bytemc.evelon.test.mongodb.simulation;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.misc.SortedOrder;
import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public final class CoinTest {

    private static final CoinRepository[] COIN_REPOSITORIES = new CoinRepository[]{
        new CoinRepository(UUID.fromString("ea88471c-2d58-407e-af5c-6c37b4909038"), 1000, 0L),
        new CoinRepository(UUID.fromString("0080de21-06a4-48ab-b682-902c979f9622"), 2000, 0L),
        new CoinRepository(UUID.fromString("c9ed8ff6-631a-4665-be5a-3b68e05b7caa"), 3000, 0L),
        new CoinRepository(UUID.fromString("d8a7a34e-0b08-4cfd-b8fc-76e4ecaf0107"), 4050, 0L),
    };
    private static Repository<CoinRepository> REPOSITORY;

    @Test
    @Disabled
    public void init() {
        Debugger.setEnable(true);
        Evelon.setCradinates(DatabaseProtocol.MONGODB, "localhost", "habi123", "root", "admin", 27017);

        REPOSITORY = Repository.create(CoinRepository.class);

        var repo = REPOSITORY.query().filter(Filter.match("uniqueId", "ea88471c-2d58-407e-af5c-6c37b4909038")).database().findFirst();
        repo.setCoins(800);
        repo.setLastUpdate(System.currentTimeMillis());
        REPOSITORY.query().database().upsert(repo);

        /*createEntries();
        sumCoins();
        sortEntries();*/
    }

    @Test
    @Disabled
    public void createEntries() {
        for (CoinRepository coinRepository : COIN_REPOSITORIES) {
            REPOSITORY.query().createIfNotExists(coinRepository);
        }
    }

    @Test
    @Disabled
    public void sumCoins() {
        var sum = REPOSITORY.query().database().sum("coins");
        System.out.println("Sum: " + sum);
    }

    @Test
    @Disabled
    public void sortEntries() {
        System.out.println("Sorted:");
        for (CoinRepository coinRepository : REPOSITORY.query().database().order("coins", 1000, SortedOrder.DESC)) {
            System.out.println(coinRepository.getCoins());
        }
    }
}
