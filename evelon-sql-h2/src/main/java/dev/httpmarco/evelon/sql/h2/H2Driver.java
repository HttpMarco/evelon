package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import org.h2.Driver;

import java.nio.file.Path;

public class H2Driver implements ProtocolDriver<H2Credentials> {

    @Override
    public void onInitialize() {
        Driver.load();
    }

    @Override
    public String jdbcString(H2Credentials credentials) {
        return "h2:" + Path.of(credentials.path()).toAbsolutePath();
    }
}
