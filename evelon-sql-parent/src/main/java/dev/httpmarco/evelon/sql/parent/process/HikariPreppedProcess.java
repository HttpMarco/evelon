package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.common.PreppedProcess;

public final class HikariPreppedProcess extends PreppedProcess<String> {

    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s%s);";

}
