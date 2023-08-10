package net.bytemc.evelon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabaseProtocol {

    MARIADB("mariadb");

    private final String driver;

    //TODO
    // MySQL
    // POSTGRESQL

}
