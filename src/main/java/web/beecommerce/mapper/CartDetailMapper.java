package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateCartDetailRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCartDetailRequestDTO;
import web.beecommerce.dto.response.CartDetailResponseDTO;
import web.beecommerce.entity.Cart;
import web.beecommerce.entity.CartDetail;
import web.beecommerce.entity.Product;

import java.util.List;

@Component
public interface CartDetailMapper {
    // map create request to entity
    CartDetail createRequestToCartDetail(CreateCartDetailRequestDTO requestDTO, Cart cart, Product product);

    // map update request to entity
    CartDetail updateRequestToCartDetail(UpdateCartDetailRequestDTO requestDTO, CartDetail cartDetail);

    // map entity to  response
    CartDetailResponseDTO mapCartDetailToCartDetailResponseDTO(CartDetail cartDetail);

    // map list entity to list response
    List<CartDetailResponseDTO> mapCartDetailListToCartDetailResponseDTOList(List<CartDetail> cartDetails);

    // map page entity to page response
    Page<CartDetailResponseDTO> mapCartDetailPageToCartDetailResponseDTOPage(Page<CartDetail> cartDetails);
    // product exit
    CartDetail productHasExistHandler(CreateCartDetailRequestDTO requestDTO, CartDetail cartDetail);
}
