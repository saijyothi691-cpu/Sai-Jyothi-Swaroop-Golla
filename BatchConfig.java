package com.swaroop.batchdemo.config;

import com.swaroop.batchdemo.model.VerificationRequest;
import com.swaroop.batchdemo.model.VerificationSummary;
import com.swaroop.batchdemo.processor.AggregatingSummaryWriter;
import com.swaroop.batchdemo.processor.VerificationRequestProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Wires the "verification summary" job: a single chunk-oriented step that
 * reads raw CSV rows, normalizes/resolves each one to a standardized code,
 * and aggregates duplicates via {@link AggregatingSummaryWriter}. A
 * {@link JobCompletionNotificationListener} then serializes the aggregated
 * results to a JSON file once the step completes.
 * <p>
 * Chunk size (10) is intentionally small for this demo dataset; production
 * jobs typically tune this based on record size and transaction cost.
 */
@Configuration
public class BatchConfig {

    private static final int CHUNK_SIZE = 10;

    @Bean
    public ItemReader<VerificationRequest> verificationRequestReader() {
        FlatFileItemReader<VerificationRequest> reader = new FlatFileItemReaderBuilder<VerificationRequest>()
                .name("verificationRequestReader")
                .resource(new ClassPathResource("input/verification-requests.csv"))
                .linesToSkip(1) // header row
                .delimited()
                .names("caseNumber", "individualId", "description", "dueDate")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(VerificationRequest.class);
                }})
                .build();
        return reader;
    }

    @Bean
    public Step verificationSummaryStep(JobRepository jobRepository,
                                         PlatformTransactionManager transactionManager,
                                         ItemReader<VerificationRequest> verificationRequestReader,
                                         VerificationRequestProcessor processor,
                                         AggregatingSummaryWriter writer) {
        return new StepBuilder("verificationSummaryStep", jobRepository)
                .<VerificationRequest, VerificationSummary>chunk(CHUNK_SIZE, transactionManager)
                .reader(verificationRequestReader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skipLimit(5)
                .skip(IllegalArgumentException.class)
                .build();
    }

    @Bean
    public Job verificationSummaryJob(JobRepository jobRepository,
                                       Step verificationSummaryStep,
                                       JobCompletionNotificationListener listener) {
        return new JobBuilder("verificationSummaryJob", jobRepository)
                .listener(listener)
                .start(verificationSummaryStep)
                .build();
    }
}
