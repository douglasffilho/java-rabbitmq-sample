package br.com.douglasffilho.orderstatus.model;

public class PaymentChange {
    public String orderId;
    public String status;

    public PaymentChange(final String orderId, final String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public PaymentChange() {}

    @Override
    public String toString() {
        return String.format("{\"orderId\":\"%s\", \"status\":\"%s\"}", this.orderId, this.status);
    }
}
