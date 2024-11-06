package web.beecommerce.util;

public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    Role(final String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }
//    public static HoaDonStatus getHoaDonStatusEnum(String name) {
//        for (HoaDonStatus status : HoaDonStatus.values()) {
//            if (name.equalsIgnoreCase(status.name))
//                return status;
//        }
//        throw new IllegalArgumentException("Không tìm thấy trạng thái hóa đơn với " + name);
//    }
//
//    public static HoaDonStatus getHoaDonStatusEnumByKey(String key) {
//        for (HoaDonStatus status : HoaDonStatus.values()) {
//            if (key.equalsIgnoreCase(status.name())) {
//                return status;
//            }
//        }
//        throw new IllegalArgumentException("Không tìm thấy trạng thái hóa đơn với key: " + key);
//    }
//
//    public static HoaDonStatus getByIndex(int index) {
//        HoaDonStatus[] statuses = HoaDonStatus.values();
//        if (index >= 0 && index < statuses.length) {
//            return statuses[index];
//        }
//        throw new IllegalArgumentException("Trạng thái không tồn tại: " + index);
//    }
}
