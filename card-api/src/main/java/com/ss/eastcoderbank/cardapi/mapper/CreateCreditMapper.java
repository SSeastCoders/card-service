package com.ss.eastcoderbank.cardapi.mapper;

import com.ss.eastcoderbank.cardapi.dto.CreateCreditDto;
import com.ss.eastcoderbank.core.model.card.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateCreditMapper {

    @Mapping(source = "nickName", target = "nickName")
    CreditCard mapToEntity(CreateCreditDto createCreditDto);
}
