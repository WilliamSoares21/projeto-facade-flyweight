package com.sacola.payment;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Transporta informações necessárias para cada tipo de pagamento.
 * Usamos um Map<String,String> extras para flexibilidade (por ex: cartão -> número, cvv).
 */
public class PaymentInfo {
    private final PaymentType type;
    private final BigDecimal amount;
    private final Map<String, String> extras;

    public PaymentInfo(PaymentType type, BigDecimal amount, Map<String, String> extras) {
        this.type = type;
        this.amount = amount;
        this.extras = extras;
    }

    public PaymentType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public Map<String, String> getExtras() { return extras; }
}
