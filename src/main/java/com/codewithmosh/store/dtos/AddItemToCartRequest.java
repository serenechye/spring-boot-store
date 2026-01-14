package com.codewithmosh.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class AddItemToCartRequest {
    @NotNull
    private Long productId;
}
