package dev.httpmarco.evelon.sql.mariadb;

import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.layer.credentials.AbstractSqlCredentials;

public class MariaDbDriver implements ProtocolDriver<AbstractSqlCredentials> {

    @Override
    public String jdbcString(AbstractSqlCredentials credentials) {
        return "mariadb://" + credentials.hostname() + ":" + credentials.port() + "/" + credentials.database() + "?serverTimezone=UTC";
    }
}
