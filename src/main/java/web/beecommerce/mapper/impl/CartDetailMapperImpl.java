package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateCartDetailRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCartDetailRequestDTO;
import web.beecommerce.dto.response.CartDetailResponseDTO;
import web.beecommerce.entity.Cart;
import web.beecommerce.entity.CartDetail;
import web.beecommerce.entity.Product;
import web.beecommerce.mapper.CartDetailMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDetailMapperImpl implements CartDetailMapper {
    @Override
    public CartDetail createRequestToCartDetail(CreateCartDetailRequestDTO requestDTO, Cart cart, Product product) {
        CartDetail cartDetail = CartDetail.builder()
                .cart(cart)
                .product(product)
                .price(requestDTO.getPrice())
                .quantity(requestDTO.getQuantity())
                .status(requestDTO.getStatus())
                .totalPrice(requestDTO.getPrice() * requestDTO.getQuantity())
                .build();
        return cartDetail;
    }

    @Override
    public CartDetail updateRequestToCartDetail(UpdateCartDetailRequestDTO requestDTO, CartDetail cartDetail) {
        cartDetail.setPrice(requestDTO.getPrice());
        cartDetail.setQuantity(requestDTO.getQuantity());
        cartDetail.setStatus(requestDTO.getStatus());
        cartDetail.setTotalPrice(requestDTO.getPrice() * requestDTO.getQuantity());
        return cartDetail;
    }

    @Override
    public CartDetailResponseDTO mapCartDetailToCartDetailResponseDTO(CartDetail cartDetail) {
        CartDetailResponseDTO cartDetailResponseDTO = CartDetailResponseDTO.builder()
                .id(cartDetail.getId())
                .cartId(cartDetail.getCart().getId())
                .productId(cartDetail.getProduct().getId())
                .quantity(cartDetail.getQuantity())
                .price(cartDetail.getPrice())
                .status(cartDetail.getStatus())
                .totalPrice(cartDetail.getTotalPrice())
                .productName(cartDetail.getProduct().getName())
                .build();
        return cartDetailResponseDTO;
    }

    @Override
    public List<CartDetailResponseDTO> mapCartDetailListToCartDetailResponseDTOList(List<CartDetail> cartDetails) {
        return cartDetails.stream().map(this::mapCartDetailToCartDetailResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<CartDetailResponseDTO> mapCartDetailPageToCartDetailResponseDTOPage(Page<CartDetail> cartDetails) {
        return null;
    }

    @Override
    public CartDetail productHasExistHandler(CreateCartDetailRequestDTO requestDTO, CartDetail cartDetail) {
        // information update : quantity
        // init  variable temp
        Integer newUpdateQuantity = requestDTO.getQuantity() + cartDetail.getQuantity();
        cartDetail.setTotalPrice(cartDetail.getPrice() * newUpdateQuantity);
        cartDetail.setQuantity(newUpdateQuantity);
        return cartDetail;
    }
}
