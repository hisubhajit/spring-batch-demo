package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileGenerationJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private Step loadDataInDB;

    @Bean
    Job fileGenerationJob(){
        return jobBuilderFactory
                .get("file-generation-job-builder")
                .start(loadDataInDB)
                .build();
    }
}
