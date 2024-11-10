package dev.httpmarco.evelon.sql.postgresql;

import dev.httpmarco.evelon.sql.parent.HikariDefaultAuthentication;
import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import lombok.SneakyThrows;

public final class PostgreSqlLayer extends HikariParentConnectionLayer<HikariDefaultAuthentication> {

    @SneakyThrows
    public PostgreSqlLayer() {
        super(new HikariDefaultAuthentication("POSTGRESQL", false));

        Class.forName("org.postgresql.Driver");
    }
}
