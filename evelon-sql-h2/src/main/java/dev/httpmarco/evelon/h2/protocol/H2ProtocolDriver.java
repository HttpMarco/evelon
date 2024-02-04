package dev.httpmarco.evelon.h2.protocol;

import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import org.h2.Driver;

public class H2ProtocolDriver implements ProtocolDriver {

    @Override
    public void onInitialize() {
        Driver.load();



    }
}
