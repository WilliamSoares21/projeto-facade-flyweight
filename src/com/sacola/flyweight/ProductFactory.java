package com.sacola.flyweight;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flyweight factory: mantém instâncias de Product e as reutiliza.
 * Reutiliza objetos Product para economizar memória
 */
public class ProductFactory {
    private static final Map<String, Product> products = new ConcurrentHashMap<>();

    // Exemplo de método para registrar produtos (poderia vir de um DB)
    public static Product getProduct(String id, String name, BigDecimal price) {
        // key baseada no id (único)
        return products.computeIfAbsent(id, k -> new Product(id, name, price));
    }

    // Busca apenas por id (quando já cadastrado)
    public static Product getProductById(String id) {
        return products.get(id);
    }
}
