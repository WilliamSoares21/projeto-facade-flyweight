package com.sacola.app;

import com.sacola.facade.PaymentFacade;
import com.sacola.flyweight.ProductFactory;
import com.sacola.model.ShoppingBag;
import com.sacola.payment.PaymentResult;
import com.sacola.payment.PaymentType;

import java.math.BigDecimal;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // --- Flyweight: registrar/obter produtos ---

        var p1 = ProductFactory.getProduct("SKU-100", "Camiseta", new BigDecimal("59.90"));
        var p2 = ProductFactory.getProduct("SKU-200", "Calça Jeans", new BigDecimal("149.90"));
        var p3 = ProductFactory.getProduct("SKU-300", "Meias (par)", new BigDecimal("9.90"));

        // --- Sacola ---
        ShoppingBag bag = new ShoppingBag();
        bag.addItem(p1, 2);
        bag.addItem(p2, 1);
        bag.addItem(p3, 5);

        System.out.println(bag);

        // --- Facade: pagar ---
        PaymentFacade facade = new PaymentFacade();

        // Exemplo 1: cartão
        var cardExtras = new HashMap<String,String>();
        cardExtras.put("cardNumber", "1234567890123456");
        cardExtras.put("holder", "FULANO");
        cardExtras.put("cvv", "123");
        cardExtras.put("expiry", "12/25");

        PaymentResult resCard = facade.pay(PaymentType.CARTAO, bag.getTotal(), cardExtras);
        System.out.println("Cartão: " + resCard);

        // Exemplo 2: boleto
        PaymentResult resBoleto = facade.pay(PaymentType.BOLETO, bag.getTotal(), new HashMap<>());
        System.out.println("Boleto: " + resBoleto);

        // Exemplo 3: pix
        PaymentResult resPix = facade.pay(PaymentType.PIX, bag.getTotal(), new HashMap<>());
        System.out.println("Pix: " + resPix);

        // Exemplo 4: espécie (pagante entrega R$ 500)
        var cashExtras = new HashMap<String,String>();
        cashExtras.put("received", "500");
        PaymentResult resCash = facade.pay(PaymentType.ESPECIE, bag.getTotal(), cashExtras);
        System.out.println("Espécie: " + resCash);
    }
}
