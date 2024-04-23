package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.LayerQuery;
import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.process.HikariPreppedProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariExecutionProcessReference;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<A extends ConnectionAuthentication> extends ConnectableLayer<HikariConnection, HikariExecutionProcessReference> {

    private HikariConnection connection;

    public HikariParentConnectionLayer(A templateCredentials) {
        super(templateCredentials, new HikariFilterHandler());
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
        runner().update(new HikariPreppedProcess(), repository);
    }

    @Override
    public ProcessRunner<HikariExecutionProcessReference> generateRunner() {
        return new HikariConnectionRunner(this.connection = new HikariConnection(protocol()));
    }

    @Override
    public <T> LayerQuery<T> query(Repository<T> repository) {
        return new HikariLayerQuery<>(repository, runner());
    }
}
