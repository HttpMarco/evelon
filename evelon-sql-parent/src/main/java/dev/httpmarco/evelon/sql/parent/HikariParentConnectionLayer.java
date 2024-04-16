package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.query.Query;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.process.HikariPreppedProcess;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<A extends ConnectionAuthentication> extends ConnectableLayer<HikariConnection, String> {

    private final HikariConnection connection;

    public HikariParentConnectionLayer(A templateCredentials) {
        super(templateCredentials, new HikariFilterHandler());
        this.connection = new HikariConnection(protocol());
    }

    public ProtocolDriver<A> protocol() {
        return ConnectionAuthentication::id;
    }

    /**
     * Calculate and create the base of the specific repository
     *
     * @param repository which is preparing for access2e34c
     */
    @Override
    public void prepped(@NotNull Repository<?> repository) {
         runner().apply(new HikariPreppedProcess(), repository);
    }

    @Override
    public ProcessRunner<String> generateRunner() {
        return new HikariConnectionRunner(this);
    }

    @Override
    public <T> Query<T> query(Repository<T> repository) {
        return new HikariLayerQuery<>(repository, runner());
    }
}
