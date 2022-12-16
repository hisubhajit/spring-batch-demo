package com.example.batch.config;

import com.example.batch.repository.PaymentRepository;
import com.example.batch.repository.entity.Payment;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDataStepConfig {

    @Autowired
    private PaymentRepository paymentRepository;

    @Bean
    Step loadDataInDB(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("load-data-in-db").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                insertDummyPaymentsData(10);
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    private void insertDummyPaymentsData(int recordCount) {
        for(int i = 0 ; i < recordCount; i++){
            insertDummyPaymentsRecord();
        }
    }

    private void insertDummyPaymentsRecord() {
        Payment payment = new Payment();
        payment.setPaymentStatus(1);
        payment.setAmount(RandomStringUtils.randomNumeric(2));
        payment.setAccountHolderName(RandomStringUtils.randomAlphabetic(10));
        payment.setClient(RandomStringUtils.randomAlphabetic(6));
        payment.setAccountNumber(RandomStringUtils.randomNumeric(10));
        payment.setBankAccountNumber(RandomStringUtils.randomNumeric(8));
        payment.setScheduleDate(LocalDate.now().plusDays(Long.valueOf(RandomStringUtils.randomNumeric(8))));
        paymentRepository.save(payment);
    }
}
