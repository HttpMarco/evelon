package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.QueryMethod;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.process.HikariPreppedProcess;
import dev.httpmarco.evelon.sql.parent.query.HikariLayerQuery;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.evelon.sql.parent.types.TypeDefaultDetector;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<A extends ConnectionAuthentication> extends ConnectableLayer<HikariConnection, HikariProcessReference> {

    private HikariConnection connection;
    private final TypeDefaultDetector detector = new TypeDefaultDetector();

    public HikariParentConnectionLayer(A templateCredentials) {
        super(templateCredentials, new HikariFilterHandler());
    }

    public ProtocolDriver<A> protocol() {
        return credentials -> {
            if (credentials instanceof HikariDefaultAuthentication auth) {
                return "jdbc:" + auth.id().toLowerCase() + "://" + auth.hostname() + ":" + auth.port() + "/" + auth.database();
            }
            return credentials.id();
        };
    }

    /**
     * Calculate and create the base of the specific repository
     *
     * @param repository which is preparing for access
     */
    @Override
    public void prepped(@NotNull Repository<?> repository) {
        runner().apply(this,  new Query<>(repository, Set.of()), new HikariPreppedProcess(this));
    }

    @Override
    public ProcessRunner<HikariProcessReference> generateRunner() {
        return new HikariConnectionRunner(this.connection = new HikariConnection(protocol()));
    }

    @Override
    public <T> QueryMethod<T> queryMethod(Repository<?> repository) {
        return new HikariLayerQuery<>(this, repository, runner());
    }
}
