package web.beecommerce.util;

public enum Payment {
    CASH_ON_DELIVERY("Cash_On_Delivery"),
    BANK_TRANFER("Bank_Transfer");
    private final String name;

    Payment(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
