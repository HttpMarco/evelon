package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
class HikariProcess<T> extends Process<T> {

    private final HikariConnectionTransmitter transmitter;

    public HikariProcess(String id, Query<T> query, boolean mustPrepare, HikariConnectionTransmitter transmitter) {
        super(id, query, mustPrepare);
        this.transmitter = transmitter;
    }
    //todo set here filters
}