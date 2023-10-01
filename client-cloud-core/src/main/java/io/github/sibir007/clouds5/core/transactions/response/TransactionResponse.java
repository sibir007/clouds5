package io.github.sibir007.clouds5.core.transactions.response;

import io.github.sibir007.clouds5.core.transactions.Transaction;

public class TransactionResponse {
    private String responseId;
    private String transactionId;
    private Transaction.Status status;
    private String description;
    private Object attachedData;

    public TransactionResponse(String cloud, String transactionId, Transaction.Status status, String description, Object attachedData) {
        this.responseId = cloud + "_" + System.currentTimeMillis();
        this.transactionId = transactionId;
        this.status = status;
        this.description = description;
        this.attachedData = attachedData;
    }

    public String getResponseId() {
        return responseId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Transaction.Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Object getAttachedData() {
        return attachedData;
    }

}
