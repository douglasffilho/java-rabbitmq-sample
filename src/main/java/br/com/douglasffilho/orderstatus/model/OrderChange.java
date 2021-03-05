package br.com.douglasffilho.orderstatus.model;

public class OrderChange {
    public String orderId;

    public OrderChange(final String orderId) {
        this.orderId = orderId;
    }

    public OrderChange() {}

    @Override
    public String toString() {
        return String.format("{\"orderId\":\"%s\"}", this.orderId);
    }
}
