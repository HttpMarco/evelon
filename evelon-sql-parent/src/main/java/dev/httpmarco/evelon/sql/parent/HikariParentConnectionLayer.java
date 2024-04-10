package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.parent.process.HikariPreppedProcess;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<A extends ConnectionAuthentication> extends ConnectableLayer<String, A, HikariConnection> {

    private final HikariConnection connection;
    private final HikariLayerProcessRunner runner;

    public HikariParentConnectionLayer(A templateCredentials) {
        super(templateCredentials);

        this.connection = new HikariConnection(protocol());
        this.runner = new HikariLayerProcessRunner(this);
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
        runner().update(repository, HikariPreppedProcess::new);
    }

}
