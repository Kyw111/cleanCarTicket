package com.cleanCar.freeTicket.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UseYn {

    Y("Y", "사용"),
    N("N", "미사용");

    private final String key;
    private final String value;
}
