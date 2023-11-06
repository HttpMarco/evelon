package net.bytemc.evelon.misc;

import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

public enum SortedOrder {

    ASC {
        @Override
        public Bson toMongo(String id) {
            return Sorts.ascending(id);
        }
    },
    DESC {
        @Override
        public Bson toMongo(String id) {
            return Sorts.descending(id);
        }
    };

    public abstract Bson toMongo(String id);

}
