package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.DatabaseDebugger;
import net.bytemc.evelon.sql.process.ColumEntryInstanceProcess;
import org.junit.jupiter.api.Test;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        DatabaseDebugger.setEnable(false);

        var repository = Repository.create(TestRepository.class);


        for (TestRepository testRepository : ColumEntryInstanceProcess.collect(repository)) {
            System.out.println(testRepository.getElement().getReason().name());
        }

        //repository.query().create(new TestRepository("polo", 99, new TestElement("hausberg 123", 20, BanReason.HACKING)));
    }
}
