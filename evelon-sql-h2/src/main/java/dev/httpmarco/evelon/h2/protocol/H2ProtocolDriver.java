package dev.httpmarco.evelon.h2.protocol;

import dev.httpmarco.evelon.h2.credentials.H2SqlCredentials;
import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import lombok.AllArgsConstructor;
import org.h2.Driver;

import java.nio.file.Path;

@AllArgsConstructor
public class H2ProtocolDriver implements ProtocolDriver<H2SqlCredentials> {

    @Override
    public void onInitialize() {
        Driver.load();
    }

    @Override
    public String jdbcString(H2SqlCredentials credentials) {
        return "h2:" + Path.of(credentials.path()).toAbsolutePath();
    }
}
