package net.bytemc.evelon.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public abstract class AbstractDateFilter<T> implements Filter<T, Date> {

    private final String id;
    private final Date value;

}
