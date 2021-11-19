package com.ss.eastcoderbank.cardapi.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class CardOptions {
    private final String nickName;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Float fromAmount;
    private final Float toAmount;
    private final Boolean activeStatus;
}
