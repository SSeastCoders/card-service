package com.ss.eastcoderbank.cardapi.dto;

import com.ss.eastcoderbank.core.model.card.CardType;
import com.ss.eastcoderbank.core.model.user.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreditCardDto {

    @NotNull(message = "Card must have id.")
    private Integer id;

    @NotNull(message = "Card must have users.")
    private List<User> users;

    private Integer cvv;


    private CardType cardType;

    private LocalDate openDate;

    private LocalDate expDate;

    @Pattern(regexp = "[a-z0-9A-Z]+", message = "username can have only alphanumeric characters.")
    @Size(min = 1, max = 20, message = "nickname must not be blank and contain no more than 20 characters.")
    @NotNull(message = "nickname must be entered.")
    private String nickName;

    private Boolean activeStatus;

    private Double interestRate;

    private Float creditLimit;

    private Float availableCredit;

    private Float balance;

    private LocalDate dueDate;

    private Float minDue;

}
