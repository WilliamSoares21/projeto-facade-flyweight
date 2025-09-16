package com.sacola.flyweight;

import java.math.BigDecimal;

/**
 * Produto imutável que representa o estado intrínseco (compartilhável).
 * Mantém dados intrínsecos (que não variam) para economizar memória
 */
public final class Product {
    private final String id;
    private final String name;
    private final BigDecimal price;

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s - %s (R$ %s)", id, name, price);
    }
}
