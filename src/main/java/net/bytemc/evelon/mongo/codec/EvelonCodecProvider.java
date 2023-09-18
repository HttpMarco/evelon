package net.bytemc.evelon.mongo.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public final class EvelonCodecProvider implements CodecProvider {

    private final ObjectMapper bsonObjectMapper;

    public EvelonCodecProvider(final ObjectMapper bsonObjectMapper) {
        this.bsonObjectMapper = bsonObjectMapper;
    }

    @Override
    public <T> Codec<T> get(final Class<T> type, final CodecRegistry registry) {
        return new EvelonMongoCodec<>(bsonObjectMapper, registry, type);
    }
}
