package web.beecommerce.util;

public enum BaseStatus {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
    public  String value;

    private BaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
