package dev.httpmarco.evelon.common.model.elements;

import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.osgan.utils.Utils;

public class AbstractSimpleParameterStage implements ElementStage<Object> {

    @Override
    public boolean isElement(Model model, Class<?> clazz) {
        return clazz.isPrimitive() || Utils.JAVA_ELEMENTS.contains(clazz);
    }

    @Override
    public Object construct(Model model, RepositoryField<?> field, ConstructProcess builder) {
        //todo
       // return builder.valuesMap().get(field.id());
        return null;
    }
}
