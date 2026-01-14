package com.codewithmosh.store.payment;

import com.codewithmosh.store.entities.Order;
import com.codewithmosh.store.exceptions.CartEmptyException;
import com.codewithmosh.store.exceptions.CartNotFoundException;
import com.codewithmosh.store.repositories.CartRepository;
import com.codewithmosh.store.repositories.OrderRepository;
import com.codewithmosh.store.services.AuthService;
import com.codewithmosh.store.services.CartService;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Configuration
public class StripeConfig {
    @Value("${spring.stripe.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey=secretKey;
    }

    @RequiredArgsConstructor
    @Service
    public static class CheckoutService {
        private final CartRepository cartRepository;
        private final OrderRepository orderRepository;
        private final AuthService authService;
        private final CartService cartService;
        private final PaymentGateway paymentGateway;

        @Transactional
        public CheckoutResponse checkout(CheckoutRequest request) {
            var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
            if(cart == null){
                throw new CartNotFoundException();
            }

            if(cart.isEmpty()){
                throw new CartEmptyException();
            }

            var order = Order.fromCart(cart, authService.getCurrentUser());
            orderRepository.save(order);

            //checkout session
            try{
                var session = paymentGateway.createCheckoutSession(order);
                cartService.clearCart(cart.getId());

                return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
            }catch(PaymentException ex){
                orderRepository.delete(order);
                throw ex;
            }
        };

        public void handleWebhookEvent(WebhookRequest request){
            paymentGateway
                    .parseWebhookRequest(request)
                    .ifPresent(paymentResult -> {
                        var order = orderRepository.findById(paymentResult.getOrderId())
                                .orElseThrow();
                        order.setStatus(paymentResult.getPaymentStatus());
                        orderRepository.save(order);
                    });
        }
    }



}
