package com.namruslan.mobilephonepayment.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "phone_balance")
@Accessors(chain = true)
@Data
public class PhoneBalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int phoneNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "balance")
    private int balance;
}
