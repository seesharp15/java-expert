package dev.expert.testing;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckoutServiceTest {

    @Test
    void chargesGatewayWithTotal() {
        var gateway = Mockito.mock(CheckoutService.PaymentGateway.class);
        when(gateway.charge(500)).thenReturn(true);
        var service = new CheckoutService(gateway);

        var cart = new CheckoutService.Cart(List.of(new CheckoutService.Item("sku-1", 1, 200), new CheckoutService.Item("sku-2", 2, 150)));
        var receipt = service.purchase(cart);

        verify(gateway).charge(500);
        assertThat(receipt.success()).isTrue();
    }

    @Test
    void surfacesGatewayFailure() {
        var gateway = Mockito.mock(CheckoutService.PaymentGateway.class);
        when(gateway.charge(Mockito.anyInt())).thenReturn(false);
        var service = new CheckoutService(gateway);
        var cart = new CheckoutService.Cart(List.of(new CheckoutService.Item("sku", 1, 100)));

        var receipt = service.purchase(cart);
        assertThat(receipt.success()).isFalse();
    }

    @Property
    void totalIsSumOfLineItems(@ForAll @Size(value = 1, max = 10) List<@IntRange(min = 1, max = 5) Integer> quantities,
                               @ForAll @Size(value = 1, max = 10) List<@IntRange(min = 1, max = 5000) Integer> prices) {
        int count = Math.min(quantities.size(), prices.size());
        var items = new java.util.ArrayList<CheckoutService.Item>();
        for (int i = 0; i < count; i++) {
            items.add(new CheckoutService.Item("sku-" + i, quantities.get(i), prices.get(i)));
        }
        var gateway = Mockito.mock(CheckoutService.PaymentGateway.class);
        when(gateway.charge(Mockito.anyInt())).thenReturn(true);
        var service = new CheckoutService(gateway);
        var cart = new CheckoutService.Cart(items);

        var receipt = service.purchase(cart);

        int expected = items.stream().mapToInt(it -> it.quantity() * it.unitPriceCents()).sum();
        assertThat(receipt.totalCents()).isEqualTo(expected);
    }
}
