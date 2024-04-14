package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.stages.SqlAbstractObjectSubStage;
import dev.httpmarco.evelon.stages.subs.AbstractParameterStage;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<A extends ConnectionAuthentication> extends ConnectableLayer<HikariConnection, String> {

    private final HikariConnection connection;

    public HikariParentConnectionLayer(A templateCredentials) {
        super(templateCredentials);

        this.connection = new HikariConnection(protocol());

        // add default type handler
        overwrite(RepositoryEntryType.OBJECT, new SqlAbstractObjectSubStage());
        overwrite(RepositoryEntryType.PARAMETER, new AbstractParameterStage());
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
        //todo
        // runner();
    }

    @Override
    public ProcessRunner<String> generateRunner() {
        return new HikariConnectionRunner(this);
    }
}
