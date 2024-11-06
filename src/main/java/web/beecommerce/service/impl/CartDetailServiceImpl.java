package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateCartDetailRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCartDetailRequestDTO;
import web.beecommerce.dto.response.CartDetailResponseDTO;
import web.beecommerce.entity.Cart;
import web.beecommerce.entity.CartDetail;
import web.beecommerce.entity.Product;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.CartDetailMapper;
import web.beecommerce.repository.CartDetailRepository;
import web.beecommerce.service.CartDetailService;
import web.beecommerce.service.CartService;
import web.beecommerce.service.ProductService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;
    private final CartDetailMapper cartDetailMapper;
    private final CartService cartService;
    private final ProductService productService;

    @Override
    public CartDetail findById(Long id) {
        return cartDetailRepository.findById(id).orElseThrow(() -> new NotFound("cartDetail not found"));
    }

    @Override
    public CartDetailResponseDTO findCartDetailById(Long id) {
        CartDetail cartDetail = cartDetailRepository.findById(id).orElseThrow(() -> new NotFound("cartDetail not found"));
        return cartDetailMapper.mapCartDetailToCartDetailResponseDTO(cartDetail);
    }

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public List<CartDetailResponseDTO> findAllCartDetailByCartId(Long cartId) {
        if (cartId == null || cartId.equals(String.valueOf(""))) {
            throw new NotFound("cartId not found");
        } else {
            List<CartDetail> cartDetails = cartDetailRepository.findCartDetailByCartId(cartId);
            return cartDetailMapper.mapCartDetailListToCartDetailResponseDTOList(cartDetails);
        }
    }

    @Override
    public List<CartDetailResponseDTO> findAllByIdCart(Long idCart) {
        List<CartDetail> cartDetail = cartDetailRepository.findAll();
        return cartDetailMapper.mapCartDetailListToCartDetailResponseDTOList(cartDetail);
    }

    @Override
    public CartDetailResponseDTO save(CreateCartDetailRequestDTO requestDTO) {
        // find cart
        Cart cart = cartService.findById(requestDTO.getCartId());
        // find product
        Product product = productService.findById(requestDTO.getProductId());

        if (cart.getId() != null && product.getId() != null) {

            // check product has Existed ?
            CartDetail cartDetail = cartDetailRepository.productExist(product.getId(), cart.getId());
            if (cartDetail != null) {
                // product had Existed
                cartDetail = cartDetailMapper.productHasExistHandler(requestDTO, cartDetail);
                cartDetailRepository.save(cartDetail);
            } else {
                // product not Existed
                cartDetail = cartDetailMapper.createRequestToCartDetail(requestDTO, cart, product);
                cartDetailRepository.save(cartDetail);
            }
            return cartDetailMapper.mapCartDetailToCartDetailResponseDTO(cartDetail);
        } else {
            throw new NotFound("product or cart not found");
        }
    }

    @Override
    public CartDetailResponseDTO update(UpdateCartDetailRequestDTO requestDTO, Long id) {
        // find
        CartDetail cartDetailUpdate = findById(id);

        CartDetail cartDetail = cartDetailMapper.updateRequestToCartDetail(requestDTO, cartDetailUpdate);
        log.info("cartDetail id : {}", cartDetail.getId());
        cartDetail = cartDetailRepository.save(cartDetail);
        return cartDetailMapper.mapCartDetailToCartDetailResponseDTO(cartDetail);
    }

    @Override
    public String delete(Long id) {
        CartDetail cartDetail = cartDetailRepository.findById(id).orElseThrow(() -> new NotFound("cartDetail not found"));
        cartDetail.setCart(null);
        cartDetail.setProduct(null);
        cartDetail = cartDetailRepository.save(cartDetail);
        if (cartDetail.getProduct() == null && cartDetail.getCart() == null) {
            cartDetailRepository.delete(cartDetail);
            return "Delete CartDetail by id : " + cartDetail.getId() + " Successfully deleted";
        } else {
            return "Delete CartDetail by id : " + cartDetail.getId() + " Failed";
        }
    }

    @Override
    public CartDetail them(CreateCartDetailRequestDTO requestDTO) {
        return cartDetailRepository.save(cartDetailMapper.createRequestToCartDetail(requestDTO, null, null));
    }

    @Override
    public CartDetail sua(UpdateCartDetailRequestDTO requestDTO) {
        return cartDetailRepository.save(cartDetailMapper.updateRequestToCartDetail(requestDTO, null));
    }

}
