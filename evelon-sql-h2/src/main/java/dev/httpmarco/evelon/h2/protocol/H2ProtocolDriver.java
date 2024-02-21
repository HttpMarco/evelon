package dev.httpmarco.evelon.h2.protocol;

import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import lombok.AllArgsConstructor;
import org.h2.Driver;

import java.nio.file.Path;

@AllArgsConstructor
public class H2ProtocolDriver implements ProtocolDriver {

    private Path path;

    @Override
    public void onInitialize() {
        Driver.load();
    }

    @Override
    public String jdbcString() {
        return "h2:" + path.toAbsolutePath();
    }
}
