package net.bytemc.evelon.mongo.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.RawBsonDocument;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

public final class EvelonMongoCodec<T> implements Codec<T> {

    private final ObjectMapper bsonObjectMapper;
    private final Codec<RawBsonDocument> rawBsonDocumentCodec;
    private final Class<T> type;

    public EvelonMongoCodec(ObjectMapper bsonObjectMapper, CodecRegistry codecRegistry, Class<T> type) {
        this.bsonObjectMapper = bsonObjectMapper;
        this.rawBsonDocumentCodec = codecRegistry.get(RawBsonDocument.class);
        this.type = type;
    }

    @SneakyThrows
    @Override
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        RawBsonDocument document = rawBsonDocumentCodec.decode(reader, decoderContext);
        return bsonObjectMapper.readValue(document.getByteBuffer().array(), type);
    }

    @SneakyThrows
    @Override
    public void encode(BsonWriter writer, Object value, EncoderContext encoderContext) {
        byte[] data = bsonObjectMapper.writeValueAsBytes(value);
        rawBsonDocumentCodec.encode(writer, new RawBsonDocument(data), encoderContext);
    }

    @Override
    public Class<T> getEncoderClass() {
        return type;
    }
}
