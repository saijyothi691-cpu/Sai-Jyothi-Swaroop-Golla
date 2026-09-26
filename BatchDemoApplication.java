package com.swaroop.batchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Verification Summary Batch demo.
 * <p>
 * This is a self-contained sample project illustrating a Spring Batch
 * chunk-oriented job: reading raw verification records from a CSV file,
 * normalizing free-text descriptions into standardized codes, aggregating
 * duplicate entries by a composite key, and writing a structured JSON
 * summary file. It mirrors the general shape of production batch jobs
 * (reader / processor / writer, restartability, structured output) without
 * containing any employer- or client-specific business logic or data.
 */
@SpringBootApplication
public class BatchDemoApplication {

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(BatchDemoApplication.class, args)));
    }
}
