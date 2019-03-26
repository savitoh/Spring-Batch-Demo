package com.br.savit0h.batchservicedemo.configuration;

import com.br.savit0h.batchservicedemo.dto.User;
import com.br.savit0h.batchservicedemo.listener.JobCompletionNotificationListener;
import com.br.savit0h.batchservicedemo.processor.UserItemProcessor;
import com.br.savit0h.batchservicedemo.reader.CustomReader;
import com.br.savit0h.batchservicedemo.writer.CustomWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends JobExecutionListenerSupport {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("import-user-job")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<User, User> chunk(10)
                .reader(new CustomReader(dataSource).readerUsers())
                .processor(new UserItemProcessor())
                .writer(new CustomWriter(dataSource))
                .build();
    }

}
