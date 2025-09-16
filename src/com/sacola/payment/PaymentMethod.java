package com.sacola.payment;

/**
 * Interface que define o contrato para diferentes métodos de pagamento
 * Permite usar o padrão Strategy para processar pagamentos de formas diferentes
 */

public interface PaymentMethod {
    PaymentResult pay(PaymentInfo info);
}
