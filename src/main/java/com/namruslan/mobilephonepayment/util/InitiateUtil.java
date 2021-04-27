package com.namruslan.mobilephonepayment.util;

import com.namruslan.mobilephonepayment.dto.BalanceDto;
import com.namruslan.mobilephonepayment.entity.PhoneBalanceEntity;
import com.namruslan.mobilephonepayment.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitiateUtil implements CommandLineRunner {

    private BalanceService balanceService;

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PhoneBalanceEntity> balanceEntityList = new ArrayList<>(
                Arrays.asList(
                        new PhoneBalanceEntity()
                        .setBalance(100)
                        .setCustomerName("Petro")
                        .setPhoneNumber(111111),
                        new PhoneBalanceEntity()
                        .setBalance(200)
                        .setCustomerName("Grigo")
                        .setPhoneNumber(222222),
                        new PhoneBalanceEntity()
                        .setBalance(300)
                        .setCustomerName("Sashka")
                        .setPhoneNumber(333333),
                        new PhoneBalanceEntity()
                        .setBalance(400)
                        .setCustomerName("Sashka")
                        .setPhoneNumber(444444),
                        new PhoneBalanceEntity()
                        .setBalance(500)
                        .setCustomerName("Grigo")
                        .setPhoneNumber(555555)
                )
        );

        balanceService.createAll(balanceEntityList);

        System.out.println("\nGet all: ");
        for (BalanceDto dto : balanceService.getAll()) System.out.println(dto);

        System.out.println("\nGet balance by id: ");
        if (balanceService.get(3).isPresent()) System.out.println(balanceService.get(3).get());
        else System.out.println("Phone balance with id 3 is not found");

        if (balanceService.get(8).isPresent()) System.out.println(balanceService.get(3).get());
        else System.out.println("Phone balance with id 8 is not found");

        System.out.println("\nGet by phone number: ");
        if (balanceService.getByPhoneNumber(444444).isPresent()) System.out.println(balanceService.getByPhoneNumber(444444).get());
        else System.out.println("Phone balance with phone number 444444 is not found");

        if (balanceService.getByPhoneNumber(888888).isPresent()) System.out.println(balanceService.getByPhoneNumber(888888).get());
        else System.out.println("Phone balance with phone number 888888 is not found");

        System.out.println("\nFind all by name: ");
        if (balanceService.findAllByCustomerName("Sashka").isEmpty()) {
            System.out.println("No phone balance with name \"Sashka\" has been found");
        } else {
            System.out.println("All balances with customer name \"Sashka\"");
            for (BalanceDto dto : balanceService.findAllByCustomerName("Sashka"))
                System.out.println(dto);
        }

        if (balanceService.findAllByCustomerName("Gosha").isEmpty()) {
            System.out.println("No phone balance with name \"Gosha\" has been found");
        } else {
            System.out.println("All balances with customer name \"Gosha\"");
            for (BalanceDto dto : balanceService.findAllByCustomerName("Gosha"))
                System.out.println(dto);
        }

        System.out.println("\nAdding 100 to balance: ");
        if (balanceService.addMoneyToBalance(111111, 100))
            System.out.println(String.format("Added %d to balance for PhoneBalance %s. Balance = %d",
                    100, balanceService.getByPhoneNumber(111111).get(), balanceService.getByPhoneNumber(111111).get().getBalance()));
        else System.out.println("No phone balance with number 111111 has been found");

        if (balanceService.addMoneyToBalance(777777, 100))
            System.out.println(String.format("Added %d to balance for PhoneBalance %s. Balance = %d",
                    100, balanceService.getByPhoneNumber(777777).get(), balanceService.getByPhoneNumber(777777).get().getBalance()));
        else System.out.println("No phone balance with number 777777 has been found");

    }
}
