package dev.httpmarco.evelon.sql.parent.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementTransmitter {

    void result(PreparedStatement statement) throws SQLException;

}
