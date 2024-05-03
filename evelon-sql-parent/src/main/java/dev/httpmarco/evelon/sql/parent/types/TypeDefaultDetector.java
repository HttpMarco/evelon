package dev.httpmarco.evelon.sql.parent.types;

import dev.httpmarco.evelon.RepositoryEntry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class TypeDefaultDetector implements TypeDetector {

    private final List<Type> detectCollection = new LinkedList<>();

    @SuppressWarnings("unchecked")
    public TypeDefaultDetector() {
        this.overwrite(Type.of("TINYINT", byte.class, Byte.class));
        this.overwrite(Type.of("INT", int.class, Integer.class));
        this.overwrite(Type.of("BIGINT", long.class, Long.class));
        this.overwrite(Type.of("FLOAT", float.class, Float.class));
        this.overwrite(Type.of("DOUBLE", double.class, Double.class));
        this.overwrite(Type.of("BOOL", boolean.class, Boolean.class));
        this.overwrite(Type.of("CHAR", char.class, Character.class));
        this.overwrite(Type.of("UUID", UUID.class));
        this.overwrite(Type.of("TEXT", String.class));

        // enums has a collection of all elements as string behind the name, we must duplicate every enum type
        this.overwrite(TypeModel.of(it -> "ENUM('" + String.join("', '", Arrays.stream(((Class<? extends Enum<?>>) it).getEnumConstants()).map(Enum::name).toList()) + "')", Class::isEnum));
    }

    @Override
    public void overwrite(Type type) {
        detectCollection.add(0, type);
    }

    @Override
    public void removeType(String typeId) {
        this.detectCollection.removeAll(this.detectCollection.stream().filter(it -> it.toString().equals(typeId)).toList());
    }

    @Contract(pure = true)
    @Override
    public @NotNull Type detect(RepositoryEntry entry) {
        return this.detectCollection.stream().filter(it -> it.predicate().test(entry.clazz())).findFirst().map(type -> type instanceof TypeModel typeModel ? typeModel.model(entry.clazz()) : type).orElseThrow(() -> new UnsupportedOperationException("For type: " + entry.clazz().getSimpleName()));
    }
}
