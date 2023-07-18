package net.bytemc.evelon.sql;

import org.jetbrains.annotations.NotNull;

public record DatabaseCradinates(String hostname, String password, String user, String database, int port) {

    public @NotNull String toUrl() {
        return "jdbc:mariadb://" + hostname + ":" + port + "/" + database + "?useUnicode=true&autoReconnect=true&user=" + user + "&password=" + password;
    }
}

