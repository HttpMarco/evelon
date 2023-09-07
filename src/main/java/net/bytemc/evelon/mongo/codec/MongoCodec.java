package net.bytemc.evelon.mongo.codec;

import lombok.SneakyThrows;
import net.bytemc.evelon.misc.Reflections;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

/**
 * Created: 07.09.2023
 *
 * @author HabsGleich
 */
public class MongoCodec<T> implements Codec<T> {

    private final Class<T> tClass;

    public MongoCodec(Class<T> clazz) {
        this.tClass = clazz;
    }

    @SneakyThrows
    @Override
    public T decode(BsonReader bsonReader, DecoderContext decoderContext) {
        T t = tClass.getConstructor().newInstance();
        while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            System.out.println(bsonReader.readName());
        }
        return t;
    }

    @Override
    public void encode(BsonWriter bsonWriter, T t, EncoderContext encoderContext) {
    }

    @Override
    public Class<T> getEncoderClass() {
        return tClass;
    }
}
