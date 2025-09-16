package com.sacola.model;

import com.sacola.flyweight.Product;

import java.math.BigDecimal;

/**
 * Item da sacola: referencia um Product (intrínseco) + quantidade (extrínseco).
 * Representa um item no carrinho de compras
 * Contém a referência ao produto (flyweight) e a quantidade selecionada
 */
public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = Math.max(1, quantity);
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = Math.max(1, quantity); }

    public BigDecimal getTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return String.format("%s x %d = R$ %s", product.getName(), quantity, getTotal());
    }
}
