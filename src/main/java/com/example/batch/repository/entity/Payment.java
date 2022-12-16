package com.example.batch.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;
    private String accountNumber;
    private String bankAccountNumber;
    private String amount;
    private String accountHolderName;
    private int paymentStatus;
    private String client;
    private LocalDate scheduleDate;
}
