package net.bytemc.evelon;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DatabaseProtocol {

    MARIADB("mariadb");

    String driver;

    //TODO
    // MySQL
    // POSTGRESQL

}
