package dev.httpmarco.evelon.transformers;

import java.lang.reflect.Field;

public interface Transformer {

    Transformer RECORD_TRANSFORMER = new RecordTransformer();

    void manipulateField(Object value, Field field, Object parent);

}
