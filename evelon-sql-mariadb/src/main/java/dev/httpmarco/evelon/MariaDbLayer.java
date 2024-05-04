package dev.httpmarco.evelon;

import dev.httpmarco.evelon.sql.parent.HikariDefaultAuthentication;
import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import dev.httpmarco.evelon.sql.parent.types.Type;

public final class MariaDbLayer extends HikariParentConnectionLayer<HikariDefaultAuthentication> {

    public MariaDbLayer() {
        super(new HikariDefaultAuthentication("MARIADB", false));

        detector().overwrite(Type.of("VARCHAR(128)", it -> it.clazz().equals(String.class) && it.hasConstant(RepositoryConstant.PRIMARY_KEY)));

        detector().overwrite(Type.of("BOOL", it -> {
            if (!(it.clazz().equals(boolean.class) || it.clazz().equals(Boolean.class))) {
                return false;
            }
            it.constant(RepositoryConstant.VALUE_REFACTOR, o -> ((boolean) o) ? 1 : 0);
            return true;
        }));
    }
}
