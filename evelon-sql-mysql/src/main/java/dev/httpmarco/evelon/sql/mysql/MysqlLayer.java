package dev.httpmarco.evelon.sql.mysql;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.sql.parent.HikariDefaultAuthentication;
import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import dev.httpmarco.evelon.sql.parent.types.Type;
import lombok.SneakyThrows;

import java.util.UUID;

public final class MysqlLayer extends HikariParentConnectionLayer<HikariDefaultAuthentication> {

    @SneakyThrows
    public MysqlLayer() {
        super(new HikariDefaultAuthentication("MYSQL", false));

        Class.forName("com.mysql.cj.jdbc.Driver");

        detector().overwrite(Type.of("CHAR(36)", it -> {

            if (it.clazz().equals(UUID.class)) {
                 it.constants().put(RepositoryConstant.VALUE_RENDERING, o -> UUID.fromString((String) o));
                return true;
            }
            return false;
        }));

        detector().overwrite(Type.of("BOOL", it -> {
            if (!(it.clazz().equals(boolean.class) || it.clazz().equals(Boolean.class))) {
                return false;
            }
            it.constants().put(RepositoryConstant.VALUE_REFACTOR, o -> ((boolean) o) ? 1 : 0);
            return true;
        }));
    }
}
