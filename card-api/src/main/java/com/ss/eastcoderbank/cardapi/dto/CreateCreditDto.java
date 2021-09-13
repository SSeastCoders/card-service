package com.ss.eastcoderbank.cardapi.dto;

import com.ss.eastcoderbank.core.model.card.CardType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateCreditDto {

    @NotNull(message = "Card must have users.")
    private List<Integer> usersIds;

    @Pattern(regexp = "[a-z0-9A-Z]+", message = "username can have only alphanumeric characters.")
    @Size(min = 1, max = 20, message = "nickname must not be blank and contain no more than 20 characters.")
    @NotNull(message = "nickname must be entered.")
    private String nickName;

}
