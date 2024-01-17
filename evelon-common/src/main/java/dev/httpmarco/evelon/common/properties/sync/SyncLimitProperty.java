package dev.httpmarco.evelon.common.properties.sync;

import dev.httpmarco.evelon.common.properties.trait.SynchronizeProperty;

public class SyncLimitProperty implements SynchronizeProperty {

    @Override
    public String id() {
        return "sync-limit";
    }
}
