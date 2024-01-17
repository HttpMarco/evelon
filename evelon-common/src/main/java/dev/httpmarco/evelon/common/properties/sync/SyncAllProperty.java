package dev.httpmarco.evelon.common.properties.sync;

import dev.httpmarco.evelon.common.properties.trait.SynchronizeProperty;

public class SyncAllProperty implements SynchronizeProperty {

    @Override
    public String id() {
        return "sync-all";
    }
}
