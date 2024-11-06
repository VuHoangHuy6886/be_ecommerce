package web.beecommerce.util;

public enum BuyAt {
    WEB("WEB"), IN_STORE("IN_STORE");
    private String value;

    BuyAt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
