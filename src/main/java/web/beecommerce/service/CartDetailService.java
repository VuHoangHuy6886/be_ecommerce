package web.beecommerce.service;

import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateCartDetailRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCartDetailRequestDTO;
import web.beecommerce.dto.response.CartDetailResponseDTO;
import web.beecommerce.entity.CartDetail;

import java.util.List;

@Service
public interface CartDetailService {
    CartDetail findById(Long id);

    CartDetailResponseDTO findCartDetailById(Long id);

    List<CartDetail> findAll();

    List<CartDetailResponseDTO> findAllCartDetailByCartId(Long cartId);

    List<CartDetailResponseDTO> findAllByIdCart(Long idCart);

    CartDetailResponseDTO save(CreateCartDetailRequestDTO requestDTO);

    CartDetailResponseDTO update(UpdateCartDetailRequestDTO requestDTO, Long id);

    String delete(Long id);

    CartDetail them(CreateCartDetailRequestDTO requestDTO);

    CartDetail sua(UpdateCartDetailRequestDTO requestDTO);

}
