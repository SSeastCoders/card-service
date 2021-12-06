package com.ss.eastcoderbank.cardapi.controller;

import com.ss.eastcoderbank.cardapi.dto.CreateCreditDto;
import com.ss.eastcoderbank.cardapi.dto.CreditCardDto;
import com.ss.eastcoderbank.cardapi.service.CardOptions;
import com.ss.eastcoderbank.cardapi.service.CreditService;
import com.ss.eastcoderbank.core.model.card.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/credit-cards")
public class CreditController {

    @Autowired
    private CreditService creditService;

    //getCreditCards()
   // @GetMapping()
   // public ResponseEntity<List<CreditCardDto>> getCreditCards() {
    //    return new ResponseEntity(creditService.getCreditCards(), HttpStatus.OK);
    //}

    @GetMapping("/dummy")
    public ResponseEntity<String> dummy() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Dummy");
    }

    //getCreditCards()
//    @GetMapping()
//    public Page<CreditCardDto> getCreditCards(@RequestParam(name="page") Integer pageNumber, @RequestParam(name="size") Integer pageSize, @RequestParam(value="asc", required = false) boolean asc, Pageable page, String sort) {
//        return creditService.getCreditCards(pageNumber, pageSize, asc, sort);
//    }
    @GetMapping
    public ResponseEntity<Page<CreditCardDto>> getCards(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) boolean asc,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "") String nickname,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false) LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false) LocalDate toDate,
            @RequestParam(required = false) Float fromAmount,
            @RequestParam(required = false) Float toAmount,
            @RequestParam(required = false) Boolean status
    ) {
        CardOptions options = new CardOptions(nickname, fromDate, toDate, fromAmount, toAmount, status);
        return new ResponseEntity(creditService.getCardsByFilter(page, size, asc, sort, options), HttpStatus.OK);
    }

    //getCreditCardByUser
    @PostMapping(value = "/user")
    public ResponseEntity<List<CreditCard>> getCreditCardsByUser(@Valid @RequestBody List<Integer> users, @RequestParam(name="page") Integer pageNumber, @RequestParam(name="size") Integer pageSize) {
        return new ResponseEntity(creditService.getCreditCardsByUser(users), HttpStatus.OK);
    }

    //getCreditCardsById()
    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable Integer id) {
        return new ResponseEntity(creditService.getCreditCardById(id), HttpStatus.OK);
    }

    //makenewcreditcard
    @PostMapping()
    public ResponseEntity<CreditCard> registration(@Valid @RequestBody CreateCreditDto card, HttpServletResponse response) {
        CreditCard creditCard = creditService.createCard(card);
        //response.addHeader("id", String.valueOf(creditCard.getId()));
        return new ResponseEntity(creditCard, HttpStatus.CREATED);
    }


    //deactiveate card


    //make a payement


    //update card


}
