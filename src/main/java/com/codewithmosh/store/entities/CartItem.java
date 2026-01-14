package com.codewithmosh.store.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

//entities do not using @data which include @ToString too and will cause issue
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@NotNull  ::  will put at Dto
    @ManyToOne()
    //@OnDelete(action = OnDeleteAction.CASCADE) :: already implemented in Database level
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //@NotNull  ::  will put at Dto
    @ManyToOne()
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public BigDecimal getTotalPrice() {
        return  product.getPrice().multiply(new BigDecimal(quantity));
    }
}