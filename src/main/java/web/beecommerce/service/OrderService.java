package web.beecommerce.service;

import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateOrderRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderRequestDTO;
import web.beecommerce.dto.response.OrderResponseDTO;
import web.beecommerce.entity.Order;

import java.util.List;

@Service
public interface OrderService {
    Order findById(Long id);

    OrderResponseDTO findOder(Long id);

    List<Order> findAll();

    OrderResponseDTO save(CreateOrderRequestDTO requestDTO);

    OrderResponseDTO update(UpdateOrderRequestDTO requestDTO, Long id);
}
