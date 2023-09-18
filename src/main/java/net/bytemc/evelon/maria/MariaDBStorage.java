package net.bytemc.evelon.maria;

import net.bytemc.evelon.sql.SQLStorage;

public final class MariaDBStorage extends SQLStorage {

    public MariaDBStorage() {
        //disable logging
        System.setProperty("mariadb.logging.disable", "false");
    }
}
