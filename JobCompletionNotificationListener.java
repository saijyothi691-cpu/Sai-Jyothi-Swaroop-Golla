package com.swaroop.batchdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swaroop.batchdemo.model.VerificationSummary;
import com.swaroop.batchdemo.processor.AggregatingSummaryWriter;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;

/**
 * On successful job completion, serializes the aggregated verification
 * summaries (built up in {@link AggregatingSummaryWriter} across every
 * chunk) into a single structured JSON output file.
 * <p>
 * Aggregation-then-serialize-on-completion is a common pattern for batch
 * jobs whose output is a rolled-up summary rather than a one-row-in /
 * one-row-out transformation.
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final AggregatingSummaryWriter aggregatingWriter;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public JobCompletionNotificationListener(AggregatingSummaryWriter aggregatingWriter) {
        this.aggregatingWriter = aggregatingWriter;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() != BatchStatus.COMPLETED) {
            System.out.println("Job did not complete successfully: " + jobExecution.getStatus());
            return;
        }

        Collection<VerificationSummary> summaries = aggregatingWriter.getAggregatedSummaries().values();

        try {
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            File outputFile = new File(outputDir, "verification-summary.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, summaries);
            System.out.println("Wrote " + summaries.size() + " aggregated summaries to " + outputFile.getPath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to write verification summary JSON output", e);
        }
    }
}
