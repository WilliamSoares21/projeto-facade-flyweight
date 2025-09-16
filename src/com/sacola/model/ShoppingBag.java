package com.sacola.model;

import com.sacola.flyweight.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Sacola de compras simples.
 */
public class ShoppingBag {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        // Se quiser podemos somar quantidades de mesmo product
        for (CartItem it : items) {
            if (it.getProduct().getId().equals(product.getId())) {
                it.setQuantity(it.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return List.copyOf(items);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Sacola ===\n");
        for (CartItem it : items) {
            sb.append(it).append("\n");
        }
        sb.append("Total: R$ ").append(getTotal()).append("\n");
        return sb.toString();
    }
}
