package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.parent.process.HikariPreppedProcess;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<CRE extends LayerConnectionCredentials> extends ConnectableLayer<String, CRE, HikariConnection> {

    private final HikariConnection connection;

    public HikariParentConnectionLayer(CRE templateCredentials) {
        super(templateCredentials, new HikariLayerProcessRunner());
        this.connection = new HikariConnection(protocol());
    }

    public ProtocolDriver<CRE> protocol() {
        return LayerConnectionCredentials::id;
    }

    /**
     * Calculate and create the base of the specific repository
     * @param repository which is preparing for access
     */
    @Override
    public void prepped(@NotNull Repository<?> repository) {
        runner().update(HikariPreppedProcess::new);
    }
}
