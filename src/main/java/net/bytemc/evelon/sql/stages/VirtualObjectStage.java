package net.bytemc.evelon.sql.stages;

public final class VirtualObjectStage {

    /*
    @Override
    public @NotNull Map<String, String> elementEntryData(@NotNull RepositoryClass<?> clazz, @Nullable Field field, Object object) {
        var entry = new HashMap<String, String>();

        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());

            if (stage instanceof ElementStage<?> elementStage) {
                entry.putAll(elementStage.anonymousElementEntryData(clazz, row, Reflections.readField(object, row)));
            }
        }
        return entry;
    }

    @Override
    public <T> T createObject(RepositoryClass<T> clazz, @Nullable Field field, StageResultSet resultSet) {
        if (clazz.clazz().isRecord()) {
            // we need to get the default constructor of the record, to set the values
            var defaultRecordConstructor = clazz.clazz().getConstructors()[0];

            if (defaultRecordConstructor == null) {
                // I cant do this shit lol
                return null;
            }

            var constructorElements = new ArrayList<>();
            for (Parameter parameter : defaultRecordConstructor.getParameters()) {
                // we need the right constructor parameter order
                constructorElements.add(resultSet.getData(parameter.getName(), parameter.getType()));
            }
            try {
                return (T) defaultRecordConstructor.newInstance(constructorElements.toArray());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            var object = Reflections.allocate(clazz.clazz());

            for (var row : clazz.getRows()) {
                var stage = StageHandler.getInstance().getElementStage(row.getType());
                if (stage instanceof ElementStage<?> elementStage) {
                    // write the fields of current object
                    var stageObject = elementStage.createObject(elementStage.newRepositoryClass(row.getType()), row, resultSet);
                    Reflections.writeField(object, row, stageObject);
                }
            }
            return object;
        }
    }
     */
}
