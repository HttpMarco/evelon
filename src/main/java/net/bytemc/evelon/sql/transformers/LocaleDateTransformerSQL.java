package net.bytemc.evelon.sql.transformers;

import net.bytemc.evelon.sql.SQLElementStageTransformer;
import net.bytemc.evelon.sql.Stage;
import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.stages.DateStageSQL;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class LocaleDateTransformerSQL implements SQLElementStageTransformer<LocalDate> {

    @Override
    public Stage<?> transformTo() {
        return StageHandler.getInstance().getStage(DateStageSQL.class);
    }

    @Override
    public Object transform(LocalDate value) {
        return Date.valueOf(value);
    }

    @Override
    public LocalDate rollback(Object value, Field field) {
        return ((Date) value).toLocalDate();
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(LocalDate.class);
    }
}
