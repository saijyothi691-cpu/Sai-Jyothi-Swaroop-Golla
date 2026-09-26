package com.swaroop.batchdemo.processor;

import com.swaroop.batchdemo.model.VerificationRequest;
import com.swaroop.batchdemo.model.VerificationSummary;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Chunk-oriented processor step: converts a single raw {@link VerificationRequest}
 * into a {@link VerificationSummary} record by resolving its free-text
 * description to a standardized code. Aggregation of duplicate
 * (case + code) combinations happens downstream in the writer.
 */
@Component
public class VerificationRequestProcessor implements ItemProcessor<VerificationRequest, VerificationSummary> {

    private final DescriptionCodeMapper codeMapper;

    @Autowired
    public VerificationRequestProcessor(DescriptionCodeMapper codeMapper) {
        this.codeMapper = codeMapper;
    }

    @Override
    public VerificationSummary process(VerificationRequest request) {
        String[] resolved = codeMapper.resolve(request.getDescription());
        String code = resolved[0];
        String canonicalDescription = resolved[1];

        VerificationSummary summary = new VerificationSummary(
                request.getCaseNumber(), code, canonicalDescription, request.getDueDate());
        summary.addIndividualId(request.getIndividualId());
        return summary;
    }
}
