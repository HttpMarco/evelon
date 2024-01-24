package dev.httpmarco.evelon.sql.parent.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementTransmitter {

    void result(PreparedStatement statement) throws SQLException;

}
