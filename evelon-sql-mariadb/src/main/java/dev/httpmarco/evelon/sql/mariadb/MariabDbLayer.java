package dev.httpmarco.evelon.sql.mariadb;

import dev.httpmarco.evelon.credentials.Credentials;
import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.layer.SqlParentLayer;
import dev.httpmarco.evelon.sql.parent.layer.credentials.AbstractSqlCredentials;

public final class MariabDbLayer extends SqlParentLayer {

    public MariabDbLayer(ProtocolDriver<? extends Credentials> driver, Credentials templateCredentials) {
        super(new MariaDbDriver(), new AbstractSqlCredentials("mariadb", "localhost", "admin", "password", "database", 3306));
    }
}
