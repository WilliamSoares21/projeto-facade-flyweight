package com.sacola.payment;

import java.math.BigDecimal;

/**
 * Pagamento em espécie: verifica se o valor entregue é suficiente.
 */
public class CashPayment implements PaymentMethod {
    @Override
    public PaymentResult pay(PaymentInfo info) {
        var extras = info.getExtras();
        String receivedStr = extras.getOrDefault("received", "0");
        BigDecimal received;
        try {
            received = new BigDecimal(receivedStr);
        } catch (NumberFormatException e) {
            return new PaymentResult(false, "Valor em espécie inválido", null);
        }

        if (received.compareTo(info.getAmount()) < 0) {
            return new PaymentResult(false, "Dinheiro insuficiente", null);
        }

        BigDecimal change = received.subtract(info.getAmount());
        return new PaymentResult(true, "Pagamento em espécie aceito. Troco: R$ " + change, "CASH-OK");
    }
}
