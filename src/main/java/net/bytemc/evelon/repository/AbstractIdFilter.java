package net.bytemc.evelon.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public abstract class AbstractIdFilter implements Filter {

    private String id;

}
