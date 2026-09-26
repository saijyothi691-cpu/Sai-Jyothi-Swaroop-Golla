package com.swaroop.batchdemo.model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents the normalized, aggregated output for a single case + code
 * combination — the result of collapsing one or more raw {@link VerificationRequest}
 * rows that share the same standardized verification code.
 */
public class VerificationSummary {

    private String caseNumber;
    private String code;
    private String codeDescription;
    private String earliestDueDate;
    private final Set<String> individualIds = new LinkedHashSet<>();

    public VerificationSummary() {
    }

    public VerificationSummary(String caseNumber, String code, String codeDescription, String dueDate) {
        this.caseNumber = caseNumber;
        this.code = code;
        this.codeDescription = codeDescription;
        this.earliestDueDate = dueDate;
    }

    public void addIndividualId(String individualId) {
        this.individualIds.add(individualId);
    }

    public void considerDueDate(String candidateDueDate) {
        // Keep the earliest due date across all merged raw records (ISO-8601 strings sort lexically).
        if (this.earliestDueDate == null || candidateDueDate.compareTo(this.earliestDueDate) < 0) {
            this.earliestDueDate = candidateDueDate;
        }
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    public String getEarliestDueDate() {
        return earliestDueDate;
    }

    public void setEarliestDueDate(String earliestDueDate) {
        this.earliestDueDate = earliestDueDate;
    }

    public Set<String> getIndividualIds() {
        return individualIds;
    }

    public String compositeKey() {
        return caseNumber + "::" + code;
    }
}
