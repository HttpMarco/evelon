package net.bytemc.evelon.sql.transformers;

import net.bytemc.evelon.sql.SQLElementStageTransformer;
import net.bytemc.evelon.sql.Stage;
import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.stages.DateStageSQL;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class LocalDateTimeTransformerSQL implements SQLElementStageTransformer<LocalDateTime> {

    @Override
    public Stage<?> transformTo() {
        return StageHandler.getInstance().getStage(DateStageSQL.class);
    }

    @Override
    public Object transform(LocalDateTime value) {
        return new Timestamp(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    @Override
    public LocalDateTime rollback(Object value, Field field) {
        return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault());
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(LocalDateTime.class);
    }
}
