package com.ss.eastcoderbank.cardapi;

import com.ss.eastcoderbank.core.model.card.CardType;
import com.ss.eastcoderbank.core.model.card.CreditCard;
import com.ss.eastcoderbank.core.model.user.Credential;
import com.ss.eastcoderbank.core.model.user.User;
import com.ss.eastcoderbank.core.model.user.UserRole;
import com.ss.eastcoderbank.core.repository.CreditRepository;
import com.ss.eastcoderbank.core.repository.UserRepository;
import com.ss.eastcoderbank.core.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

@Profile("h2")
@Component
@AllArgsConstructor
public class PopulateDatabase implements ApplicationRunner {


    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private CreditRepository creditRepository;


    public void run(ApplicationArguments args) {
        UserRole userRoleAdmin = new UserRole();
        userRoleAdmin.setId(1);
        userRoleAdmin.setTitle("Admin");

        UserRole userRoleCust = new UserRole();
        userRoleCust.setId(2);
        userRoleCust.setTitle("Customer");

        userRoleRepository.saveAll(Arrays.asList(userRoleAdmin, userRoleCust));

        User user = new User();
        user.setActiveStatus(true);
        user.setId(1);
        user.setEmail("hazel@smoothstack.com");
        user.setFirstName("hazel");
        Credential cred = new Credential();
        cred.setUsername("hazel");
        cred.setPassword("hazel");
        user.setCredential(cred);
        user.setRole(userRoleAdmin);

        userRepository.save(user);

        User userCust = new User();
        userCust.setActiveStatus(true);
        userCust.setId(2);
        userCust.setEmail("customer@smoothstack.com");
        userCust.setFirstName("customer");
        Credential credCust = new Credential();
        credCust.setUsername("customer");
        credCust.setPassword("customer");
        userCust.setCredential(credCust);
        userCust.setRole(userRoleCust);

        userRepository.save(userCust);

        IntStream.rangeClosed(1, 30).forEach(i -> {
                    User user2 = new User();
                    user2.setActiveStatus(true);
                    user2.setId(i+2);
                    user2.setEmail("user" + i + "@smoothstack.com");
                    user2.setFirstName("firstName" + i);
                    user2.setLastName("lastName" + i);
                    Credential cred2 = new Credential();
                    cred2.setUsername("user" + i);
                    cred2.setPassword("hazelasd");
                    user2.setCredential(cred2);
                    user2.setRole(userRoleAdmin);
                    userRepository.save(user2);
                });
        //(1, TRUE, 'Boston', 'MA', '41 Bothwell Road', 02135, 'tempPass', 'hazel', '2021-07-07', '1996-06-28', 'hazel.baker-harvey@smoothstack.com', 'Hazel', 'Baker-Harvey', '(206) 557-0334', 1);

        CreditCard creditCard = new CreditCard();
        creditCard.setAvailableCredit(100F);
        creditCard.setDueDate(LocalDate.now());
        creditCard.setBalance(100F);
        creditCard.setCardType(CardType.CREDIT);
        creditCard.setMinDue(25f);
        creditCard.setExpDate(LocalDate.now());
        creditCard.setInterestRate(0.01);
        creditCard.setCvv("433");
        creditCard.setSwipe("2893742983742944");
        creditCard.setOpenDate(LocalDate.now());
        creditCard.setNickName("Defbrfj");
        creditCard.setCreditLimit(1020F);
        creditCard.setActiveStatus(true);

        creditRepository.save(creditCard);

    }
}
