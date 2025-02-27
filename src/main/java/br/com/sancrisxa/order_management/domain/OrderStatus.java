package br.com.sancrisxa.order_management.domain;

public enum OrderStatus {
    PENDING("Pendente"),
    PROCESSING("Processando"),
    SHIPPED("Enviado"),
    DELIVERED("Entregue"),
    CANCELLED("Cancelado");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
