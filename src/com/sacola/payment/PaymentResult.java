package com.sacola.payment;

/**
 * Resultado padrão para operações de pagamento.
 */
public class PaymentResult {
    private final boolean success;
    private final String message;
    private final String transactionId; // pode ser boleto num, pix key, etc

    public PaymentResult(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getTransactionId() { return transactionId; }

    @Override
    public String toString() {
        return String.format("success=%s, msg=%s, tx=%s", success, message, transactionId);
    }
}
