package com.swaroop.batchdemo.model;

/**
 * Represents one raw row read from the input CSV file — an individual
 * verification request tied to a case and household member.
 */
public class VerificationRequest {

    private String caseNumber;
    private String individualId;
    private String description;
    private String dueDate;

    public VerificationRequest() {
    }

    public VerificationRequest(String caseNumber, String individualId, String description, String dueDate) {
        this.caseNumber = caseNumber;
        this.individualId = individualId;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "VerificationRequest{" +
                "caseNumber='" + caseNumber + '\'' +
                ", individualId='" + individualId + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
