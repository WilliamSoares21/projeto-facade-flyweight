package com.sacola.payment;

import java.util.UUID;

/**
 * Simula geração de boleto.
 */
public class BoletoPayment implements PaymentMethod {
    @Override
    public PaymentResult pay(PaymentInfo info) {
        // Em real: gerar código de barras/linha digitável, vencimento, etc.
        String boletoNumber = "BOLETO-" + UUID.randomUUID();
        return new PaymentResult(true, "Boleto gerado. Pagar até a data de vencimento.", boletoNumber);
    }
}
