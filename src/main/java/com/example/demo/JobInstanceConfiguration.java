package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobInstanceConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobExample02() {
        return jobBuilderFactory.get("job")
                .start(step1Example03())
                .next(step2Example03())
                .build();
    }

    @Bean
    public Step step1Example03() {
        return stepBuilderFactory.get("step1-example03")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        JobParameters jobParameters = stepContribution.getStepExecution().getJobExecution().getJobParameters();


                        System.out.println("======================");

                        String name = jobParameters.getString("name");
                        Long seq = jobParameters.getLong("seq");
                        Double age = jobParameters.getDouble("age");
                        Date date = jobParameters.getDate("date");

                        System.out.println("date = " + date);
                        System.out.println("age = " + age);
                        System.out.println("seq = " + seq);
                        System.out.println("name = " + name);

                        System.out.println("======================");

                        Map<String, Object> jopParameterHashMap = chunkContext.getStepContext().getJobParameters();

                        System.out.println("======================");
                        System.out.println("JOB PARAMETER HASH MAP");

                        name = (String) jopParameterHashMap.get("name");
                        seq = (Long) jopParameterHashMap.get("seq");
                        age = (Double) jopParameterHashMap.get("age");
                        date = (Date) jopParameterHashMap.get("date");


                        System.out.println("date = " + date);
                        System.out.println("age = " + age);
                        System.out.println("seq = " + seq);
                        System.out.println("name = " + name);
                        System.out.println("======================");



                        System.out.println("STEP1 Was Executed!!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step2Example03() {
        return stepBuilderFactory.get("step2-example03")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("STEP2 Was Executed!!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
