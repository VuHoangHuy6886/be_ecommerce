package web.beecommerce.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class GenericSpecification<T> {
    // function compare correct attributes value

    // ham so sanh gia tri chinh xac
    public Specification<T> hasEqual(String attributeName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || (value instanceof String && !StringUtils.hasText((String) value))) {
                return null;
            }
            return criteriaBuilder.equal(root.get(attributeName), value);
        };
    }

    // Hàm để so khớp giá trị LIKE (dành cho chuỗi)
    public Specification<T> hasLike(String attributeName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(value)) {
                return null;
            }
            return criteriaBuilder.like(root.get(attributeName), "%" + value + "%");
        };
    }

    // Hàm để so khớp giá trị lớn hơn hoặc bằng (dành cho số)
    public Specification<T> hasGreaterThanOrEqualTo(String attributeName, Double value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(attributeName), value);
        };
    }

    // Hàm để so khớp giá trị nhỏ hơn hoặc bằng (dành cho số)
    public Specification<T> hasLessThanOrEqualTo(String attributeName, Double value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(attributeName), value);
        };
    }

    // Hàm để so khớp giá trị giữa khoảng (dành cho số)
    public Specification<T> between(String attributeName, Double startValue, Double endValue) {
        return (root, query, criteriaBuilder) -> {
            if (startValue == null || endValue == null) {
                return null;
            }
            return criteriaBuilder.between(root.get(attributeName), startValue, endValue);
        };
    }

    // ham join
    public Specification<T> hasCategoryId(String categoryAttribute, Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null; // Không lọc nếu không có categoryId
            }
            return criteriaBuilder.equal(root.join(categoryAttribute).get("id"), categoryId);
        };
    }
}
