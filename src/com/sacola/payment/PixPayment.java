package com.sacola.payment;

import java.util.UUID;

/**
 * Simula pagamento por Pix (instantâneo).
 */
public class PixPayment implements PaymentMethod {
    @Override
    public PaymentResult pay(PaymentInfo info) {
        // Em real: integração com PSP/BRB/itau para QR/tx
        String pixId = "PIX-" + UUID.randomUUID();
        return new PaymentResult(true, "Pix realizado com sucesso (simulado).", pixId);
    }
}
