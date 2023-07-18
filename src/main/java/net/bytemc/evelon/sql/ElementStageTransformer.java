package net.bytemc.evelon.sql;

public interface ElementStageTransformer extends Stage<Object> {

    Stage<?> transformTo();

}
