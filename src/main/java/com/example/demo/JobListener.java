package com.example.demo;

import org.springframework.batch.core.JobExecution;

public class JobListener implements org.springframework.batch.core.JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().putString("name", "user1");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
