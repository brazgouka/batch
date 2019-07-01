package com.example.batch.config;

import com.example.batch.models.Policy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Policy> itemReader,
                   ItemWriter<Policy> itemWriter) {

        Step stepOne = stepBuilderFactory.get("file-db")
                .<Policy, Policy> chunk(500)
                .reader(itemReader)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(stepOne)
                .build();
    }

    @Bean
    public FlatFileItemReader <Policy> fileReader(@Value("${input}") Resource in) {
        return new FlatFileItemReaderBuilder<Policy>()
                .name("file-reader")
                .resource(in)
                .targetType(Policy.class)
                .delimited().delimiter(",")
                .names(new String[]{"policyID", "stateCode", "country"})
                .linesToSkip(1)
                .build();
    }

}

