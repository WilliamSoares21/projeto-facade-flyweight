package com.sacola.payment;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Simula pagamento por cartão.
 * Em produção: comunicaria com gateway, trataria tokens, etc.
 */
public class CardPayment implements PaymentMethod {

    @Override
    public PaymentResult pay(PaymentInfo info) {
        // validações básicas
        var extras = info.getExtras();
        String cardNumber = extras.getOrDefault("cardNumber", "");
        String holder = extras.getOrDefault("holder", "");
        String cvv = extras.getOrDefault("cvv", "");
        String expiry = extras.getOrDefault("expiry", "");

        if (cardNumber.isBlank() || holder.isBlank() || cvv.isBlank()) {
            return new PaymentResult(false, "Dados do cartão incompletos", null);
        }

        // Simulação: validação simples do tamanho do número (não é Luhn aqui para simplicidade)
        if (cardNumber.length() < 12) {
            return new PaymentResult(false, "Número de cartão inválido", null);
        }

        // Simula autorização (aceita valores até R$ 10.000 para demo)
        BigDecimal limit = new BigDecimal("10000");
        if (info.getAmount().compareTo(limit) > 0) {
            return new PaymentResult(false, "Valor excede limite do cartão (simulação)", null);
        }

        String tx = "CARD-" + UUID.randomUUID();
        return new PaymentResult(true, "Pagamento por cartão autorizado", tx);
    }
}
