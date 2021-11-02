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
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        log.info("Finding all cards, not paginated");
        return creditRepository.findAll();
    }

    public Page<CreditCardDto> getCreditCards(Integer pageNumber, Integer pageSize, boolean asc, String sort) {
        log.info("Request for paginated results of all cards...");
        Page<CreditCardDto> req;
        if (sort != null) {
            log.info("Results sorted by {}...", sort);
            req = creditRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sort))).map(card -> cardMapper.mapToDto(card));
        } else {
            log.info("Results not sorted...");
            req = creditRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(card -> cardMapper.mapToDto(card));
        }
        log.info("Returning Page of CreditCardDto...");
        return req;
    }

    public CreditCardDto getCreditCardsByUser(Integer userId) {
        log.info("Request for listed results of all Credit Cards by User Id: {}...", userId);
        List<User> cardUsers = new ArrayList<User>();
        cardUsers.add(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
        log.info("Returning List of CreditCards...");
        return cardMapper.mapToDto(creditRepository.findCreditCardsByUsersIn(cardUsers).orElseThrow(CardNotFoundException::new));
    }

    public CreditCardDto getCreditCardById(Integer id) {
        log.info("Request for Credit Cards by id: {}...", id);
        return cardMapper.mapToDto(creditRepository.findById(id).orElseThrow(CardNotFoundException::new));
    }

    public Optional<CreditCardDto> createCard(CreateCreditDto card) {

        log.info("Request for new Credit Card...");

        log.info("Mapping request DTO to credit card object...");
        CreditCard creditCard = createMapper.mapToEntity(card);

        log.info("Transforming user ids into user list...");
        List<User> cardUsers = card.getUsersIds().stream().map(user -> (userRepository.findById(user)).orElseThrow(UserNotFoundException::new)).collect(Collectors.toList());

        log.info("Setting user list of new credit card...");
        creditCard.setUsers(cardUsers);

        log.info("Applying default credit card setting to new card...");
        creditCard.DefaultCredit();

        try {
            log.info("Saving new credit card to database...");
            creditRepository.save(creditCard);
            log.info("Returning new credit card...");
            return Optional.of(cardMapper.mapToDto(creditCard));
        } catch (DataIntegrityViolationException e) {
            log.warn("Card cannot be saved due to data integrity violation");
            return Optional.of(null);
        }
    }

}
