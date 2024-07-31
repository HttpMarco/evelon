package dev.httpmarco.evelon.sql.mysql;

import dev.httpmarco.evelon.sql.parent.HikariDefaultAuthentication;
import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import lombok.SneakyThrows;

public final class MysqlLayer extends HikariParentConnectionLayer<HikariDefaultAuthentication> {

    @SneakyThrows
    public MysqlLayer() {
        super(new HikariDefaultAuthentication("MYSQL", false));

        Class.forName("com.mysql.cj.jdbc.Driver");

       // detector().overwrite(Type.of("VARCHAR(128)", it -> it.clazz().equals(String.class) && it.constants().has(RepositoryConstant.PRIMARY_KEY)));
    }
}
