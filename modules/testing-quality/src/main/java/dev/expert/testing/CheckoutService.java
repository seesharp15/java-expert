package dev.expert.testing;

import java.util.List;
import java.util.Objects;

public class CheckoutService {
    private final PaymentGateway gateway;

    public CheckoutService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public Receipt purchase(Cart cart) {
        throw new UnsupportedOperationException("TODO: implement purchase logic");
    }

    public record Receipt(int totalCents, boolean success) { }

    public record Cart(List<Item> items) {
        public Cart {
            Objects.requireNonNull(items);
        }
    }

    public record Item(String sku, int quantity, int unitPriceCents) {
        public Item {
            Objects.requireNonNull(sku);
            if (quantity <= 0 || unitPriceCents < 0) {
                throw new IllegalArgumentException("Invalid cart item");
            }
        }
    }

    public interface PaymentGateway {
        boolean charge(int cents);
    }
}

























































/*
ANSWER KEY:

public Receipt purchase(Cart cart) {
    int total = cart.items().stream()
        .mapToInt(i -> i.quantity() * i.unitPriceCents())
        .sum();
    boolean success = gateway.charge(total);
    return new Receipt(total, success);
}
*/
