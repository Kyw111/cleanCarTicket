package com.cleanCar.freeTicket.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DelYn {

    Y("Y", "삭제"),
    N("N", "미삭제");

    private final String key;
    private final String value;
}
