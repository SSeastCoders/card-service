package com.ss.eastcoderbank.cardapi.dto;

import com.ss.eastcoderbank.core.model.card.Card;
import com.ss.eastcoderbank.core.model.card.CardType;
import com.ss.eastcoderbank.core.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreditCardDto {

    private Integer id;

    private List<User> users;

    private Integer cvv;

    private CardType cardType;

    private LocalDate openDate;

    private LocalDate expDate;

    private String nickName;

    private Boolean activeStatus;

    private Double interestRate;

    private Float creditLimit;

    private Float availableCredit;

    private Float balance;

    private LocalDate dueDate;

    private Float minDue;

}
