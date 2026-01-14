package com.codewithmosh.store.entities;

import com.codewithmosh.store.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //tell hibernate it will generate using db, only use for ID or annotation with ID
    @Column(name = "id")
    private UUID id;

//    @NotNull   ::  will put at Dto
    @Column(name = "date_created", insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart", cascade =  CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER) //set to eager because whenever fetch cart, need fetch item
    private Set<CartItem> items = new LinkedHashSet<>();

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CartItem getItem(Long productId) {
        return items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(null);
    }

    public CartItem addItem(Product product) {
        var cartItem = getItem(product.getId());
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }else{
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(this);
            items.add(cartItem);
        }

        return cartItem;
    }

    public CartItem removeItem(Long productId) {
        var cartItem = getItem(productId);
        if(cartItem != null) {
            items.remove(cartItem);
        }
        return cartItem;
    }

    public void clear(){
        items.clear();
    }

    public Boolean isEmpty(){
        return  items.isEmpty();
    }

}