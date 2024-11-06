package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.beecommerce.entity.Cart;
import web.beecommerce.exception.NotFound;
import web.beecommerce.repository.CartRepository;
import web.beecommerce.service.CartService;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFound("Cart not found"));
    }

    @Override
    public Cart findCartByCustomerID(Long customerID) {
//        return cartRepository.findCartByUserId(userID).orElseThrow(() -> new NotFound("cart not found"));
        return null;
    }

}
