package dev.httpmarco.evelon;

import dev.httpmarco.evelon.sql.parent.HikariDefaultAuthentication;
import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;

public final class MariaDbLayer extends HikariParentConnectionLayer<HikariDefaultAuthentication> {

    public MariaDbLayer() {
        super(new HikariDefaultAuthentication("MARIADB", false));
    }
}
