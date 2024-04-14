package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.stages.SingleStage;
import org.jetbrains.annotations.NotNull;

public final class HikariPreppedProcess extends Process<String> {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS %s (%s%s);";

    @Override
    public String run(RepositoryEntry entry, @NotNull Layer<String> layer) {
        var objectEntry = (RepositoryExternalEntry) entry;

        // todo search subs

        return CREATE_TABLE_SQL.formatted(entry.id(), String.join(", ",
                        objectEntry.children().stream()
                                .filter(it -> it.stage(layer).isSingleStage())
                                .map(it -> ((SingleStage<String>) it.stage(layer)).transform(it))
                                .toList())
                , "");
    }
}
