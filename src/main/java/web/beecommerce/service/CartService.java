package web.beecommerce.service;

import org.springframework.stereotype.Service;
import web.beecommerce.entity.Cart;

@Service
public interface CartService {
    void save(Cart cart);

    Cart findById(Long id);

    Cart findCartByCustomerID(Long customerID);
}
