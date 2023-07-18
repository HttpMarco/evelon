package net.bytemc.evelon.test;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.DatabaseDebugger;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultTestBootstrap {

    @Test
    public void bootTest() {


        DatabaseDebugger.setEnable(true);

        var repository = Repository.create(TestRepository.class);

        List all = repository.query().filter().match("name", "HttpMarco").complete().database().findAll();



        /*
        DatabaseDebugger.setEnable(true);



        var history = new ArrayList<String>();

        history.add("FlxwDNS");
        history.add("Versandkosten");
        history.add("TrixdHD");

        repository.query().create(new TestRepository("TrixdHD", 3000, history));

        //  System.out.println(repository.query().database().count());

         */

    }
}
