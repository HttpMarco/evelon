/*
 * Copyright 2019-2023 ByteMC team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bytemc.evelon.repository.filters;

import com.mongodb.client.model.Filters;
import net.bytemc.evelon.local.LocalStorageHelper;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.AbstractIdFilter;
import org.bson.conversions.Bson;

import java.util.stream.Stream;

public final class BetweenFilter extends AbstractIdFilter {

    private final Object minimumBounce;
    private final Object maximumBounce;

    public BetweenFilter(String id, Object minimumBounce, Object maximumBounce) {
        super(id);

        if(!Reflections.isNumber(minimumBounce.getClass()) || !Reflections.isNumber(maximumBounce.getClass())) {
            throw new IllegalArgumentException("The minimumBounce and maximumBounce must be a number.");
        }

        this.minimumBounce = minimumBounce;
        this.maximumBounce = maximumBounce;
    }

    @Override
    public String sqlFilter() {
        return getId() + " BETWEEN " + minimumBounce + " AND " + maximumBounce;
    }

    @Override
    public Bson mongoFilter() {
        return Filters.and(Filters.gte(getId(), minimumBounce), Filters.lte(getId(), maximumBounce));
    }

    @Override
    public <T> Stream<T> localFilter(Stream<T> stream) {
        // maybe we must check every fall of number types and not only the doubles.
        return stream.filter(it -> {
            // read field from an object
            var numberFilter = LocalStorageHelper.getNumberFilter(getId(), it);

            return numberFilter != null && numberFilter.doubleValue() >= ((Number) minimumBounce).doubleValue() && numberFilter.doubleValue() <= ((Number) maximumBounce).doubleValue();
        });
    }
}
