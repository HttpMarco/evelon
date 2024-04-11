package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.repository.Repository;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<A extends ConnectionAuthentication> extends ConnectableLayer<HikariConnection> {

    private final HikariConnection connection;

    public HikariParentConnectionLayer(A templateCredentials) {
        super(templateCredentials);

        this.connection = new HikariConnection(protocol());
    }

    public ProtocolDriver<A> protocol() {
        return ConnectionAuthentication::id;
    }

    /**
     * Calculate and create the base of the specific repository
     *
     * @param repository which is preparing for access
     */
    @Override
    public void prepped(@NotNull Repository<?> repository) {
        //todo
    }
}
