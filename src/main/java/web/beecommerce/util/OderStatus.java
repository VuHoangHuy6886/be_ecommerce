package web.beecommerce.util;

public enum OderStatus {
    CREATE_ORDER("CREATE_ORDER"), // KHỞI TẠO
    PENDING_ORDER("PENDING_ORDER"), //  CHỜ XỦ LÝ
    CONFIRMED_ORDER("CONFIRMED_ORDER"), // ĐÃ  XÁC NHẬN
    CANCELLED_ORDER("CANCELLED_ORDER"), // ĐÃ HỦY
    DELIVERED("DELIVERED"); // ĐÃ HOÀN THÀNH

    private String value;

    private OderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
