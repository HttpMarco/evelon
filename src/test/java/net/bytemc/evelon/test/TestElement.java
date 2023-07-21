package net.bytemc.evelon.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestElement {

    private final String address;
    private final int coolness;
    private final BanReason reason;

}
