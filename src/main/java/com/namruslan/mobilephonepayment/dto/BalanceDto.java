package com.namruslan.mobilephonepayment.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class BalanceDto {

    private int id;

    private int phoneNumber;

    private String customerName;

    private int balance;

}
