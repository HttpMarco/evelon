package net.bytemc.evelon.h2;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

public final class H2Connection {

    @Getter @Setter
    private static Path path = Path.of("h2_database");

}
