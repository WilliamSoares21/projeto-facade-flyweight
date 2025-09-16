package com.sacola.facade;

import com.sacola.payment.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Fachada que encapsula os detalhes dos diferentes métodos de pagamento.
 * implementa o padrão Facade, simplificando o processo de pagamento
 * Esconde a complexidade das operações de pagamento para os clientes
 */
public class PaymentFacade {
    // poderíamos injetar dependências ou usar um registrador (Map) para os métodos
    private final Map<PaymentType, PaymentMethod> registry = new HashMap<>();

    public PaymentFacade() {
        // registra implementações (poderia vir por DI)
        registry.put(PaymentType.CARTAO, new CardPayment());
        registry.put(PaymentType.BOLETO, new BoletoPayment());
        registry.put(PaymentType.PIX, new PixPayment());
        registry.put(PaymentType.ESPECIE, new CashPayment());
    }

    /**
     * Método simples para processar pagamento:
     *  - valida amount
     *  - seleciona método
     *  - executa pay
     */
    public PaymentResult pay(PaymentType type, BigDecimal amount, Map<String, String> extras) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new PaymentResult(false, "Valor inválido para pagamento", null);
        }

        PaymentMethod method = registry.get(type);
        if (method == null) {
            return new PaymentResult(false, "Método de pagamento não suportado", null);
        }

        PaymentInfo info = new PaymentInfo(type, amount, extras);
        // A fachada poderia orquestrar passos adicionais: logging, persistência, notificações
        return method.pay(info);
    }
}
