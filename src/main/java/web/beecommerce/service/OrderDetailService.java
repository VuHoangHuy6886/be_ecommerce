package web.beecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateOrderDetailDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderDetailDTO;
import web.beecommerce.dto.response.OrderDetailResponseDTO;

import java.util.List;

@Service
public interface OrderDetailService {
    OrderDetailResponseDTO save(CreateOrderDetailDTO request);

    OrderDetailResponseDTO update(UpdateOrderDetailDTO request);

    OrderDetailResponseDTO get(Long id);

    List<OrderDetailResponseDTO> getAllByOrderId(Long orderId);

    Page<OrderDetailResponseDTO> getAll(Integer page, Integer size);
}
