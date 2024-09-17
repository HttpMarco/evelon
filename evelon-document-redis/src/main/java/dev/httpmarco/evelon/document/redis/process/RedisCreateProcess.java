package dev.httpmarco.evelon.document.redis.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.common.JsonUtils;
import dev.httpmarco.evelon.common.Reflections;
import dev.httpmarco.evelon.document.redis.RedisFilter;
import dev.httpmarco.evelon.document.redis.RedisProcessReference;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public final class RedisCreateProcess extends UpdateProcess<RedisProcessReference, RedisFilter<?>> {

    private final Object value;

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, RedisProcessReference reference) {
        for (var child : entry.children()) {
            var childValue = Reflections.value(child.constants().constant(RepositoryConstant.PARAM_FIELD), value);
            if (child instanceof RepositoryExternalEntry externalEntry) {
                //todo
                return;
            }
            reference.data().add(child.id(), JsonUtils.GSON.toJsonTree(childValue));
        }
        System.out.println(reference.data());
    }
}
