package com.ss.eastcoderbank.cardapi.service;

import com.ss.eastcoderbank.cardapi.dto.CreateCreditDto;
import com.ss.eastcoderbank.cardapi.mapper.CreateCreditMapper;
import com.ss.eastcoderbank.core.exeception.CardNotFoundException;
import com.ss.eastcoderbank.core.exeception.UserNotFoundException;
import com.ss.eastcoderbank.core.model.card.CreditCard;
import com.ss.eastcoderbank.core.model.user.User;
import com.ss.eastcoderbank.core.repository.CreditRepository;
import com.ss.eastcoderbank.core.repository.UserRepository;
import com.ss.eastcoderbank.core.transferdto.CreditCardDto;
import com.ss.eastcoderbank.core.transfermapper.CardMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {

    private CreditRepository creditRepository;
    private UserRepository userRepository;
    private CardMapper cardMapper;
    private CreateCreditMapper createMapper;

    public CreditService(CreditRepository creditRepository, UserRepository userRepository, CardMapper cardMapper, CreateCreditMapper createMapper) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
        this.cardMapper = cardMapper;
        this.createMapper = createMapper;
    }

    public List<CreditCard> getCreditCards() {
        return creditRepository.findAll();
    }

    public Page<CreditCardDto> getCreditCards(Integer pageNumber, Integer pageSize, boolean asc, String sort) {
        Page<CreditCardDto> req;
        if (sort != null) {
            req = creditRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sort))).map(card -> cardMapper.mapToDto(card));
        } else {
            req = creditRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(card -> cardMapper.mapToDto(card));
        }
        return req;
    }

    public List<CreditCard> getCreditCardsByUser(List<Integer> users){
        List<User> cardUsers = users.stream().map(user -> (userRepository.findById(user)).orElseThrow(UserNotFoundException::new)).collect(Collectors.toList());
        return creditRepository.findCreditCardsByUsersIn(cardUsers);
    }

    public CreditCard getCreditCardById(Integer id){
        return creditRepository.findById(id).orElseThrow(CardNotFoundException::new);
    }

    public Page<CreditCardDto> getCardsByFilter(Integer pageNumber, Integer pageSize, boolean asc, String sort, CardOptions options) {
        Pageable pageable = (sort != null) ? PageRequest.of(pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC: Sort.Direction.DESC, sort)) : PageRequest.of(pageNumber, pageSize);
        return creditRepository.findCreditCardByFilter(options.getNickName(), options.getFromDate(), options.getToDate(), options.getFromAmount(),
                options.getToAmount(), options.getActiveStatus(), pageable).map(creditCard -> cardMapper.mapToDto(creditCard));
    }

    public CreditCard createCard(CreateCreditDto card){

        CreditCard creditCard = createMapper.mapToEntity(card);

        //-> nickname, users
        List<User> cardUsers = card.getUsersIds().stream().map(user -> (userRepository.findById(user)).orElseThrow(UserNotFoundException::new)).collect(Collectors.toList());
        creditCard.setUsers(cardUsers);

        creditCard.DefaultCredit();

        try {
            creditRepository.save(creditCard);
            return(creditCard);
           //r/eturn user.getId();
        } catch (DataIntegrityViolationException e) {
            //Throwable t = e.getCause();
            //if (t instanceof ConstraintViolationException) {
            //    handleUniqueConstraints(((ConstraintViolationException) t).getConstraintName());
           // }
            //log.info(e.getMessage(), e);
           // throw e; // something went wrong.
        }


    return null;

       // creditRepository.save(card);
       // return
    }

}
