package com.swaroop.batchdemo.processor;

import com.swaroop.batchdemo.model.VerificationSummary;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Aggregates processed {@link VerificationSummary} records across the whole
 * job run, merging entries that share the same case + code composite key
 * (e.g. two raw rows both resolving to "VER-INC" for the same case) while
 * preserving each distinct individual ID and keeping the earliest due date.
 * <p>
 * The merged results are exposed via {@link #getAggregatedSummaries()} for
 * a job-completion listener to serialize once the step finishes.
 */
@Component
public class AggregatingSummaryWriter implements ItemWriter<VerificationSummary> {

    private final Map<String, VerificationSummary> aggregated = new LinkedHashMap<>();

    @Override
    public void write(Chunk<? extends VerificationSummary> chunk) {
        for (VerificationSummary incoming : chunk) {
            aggregated.merge(incoming.compositeKey(), incoming, this::merge);
        }
    }

    private VerificationSummary merge(VerificationSummary existing, VerificationSummary incoming) {
        incoming.getIndividualIds().forEach(existing::addIndividualId);
        existing.considerDueDate(incoming.getEarliestDueDate());
        return existing;
    }

    public Map<String, VerificationSummary> getAggregatedSummaries() {
        return aggregated;
    }
}
