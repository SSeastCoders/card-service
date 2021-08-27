package com.ss.eastcoderbank.cardapi.service;

import com.ss.eastcoderbank.core.model.card.CreditCard;
import com.ss.eastcoderbank.core.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    public List<CreditCard> getCreditCards() {
        return creditRepository.findAll();
    }


}
