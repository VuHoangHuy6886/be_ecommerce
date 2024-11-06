package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateOrderRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderRequestDTO;
import web.beecommerce.dto.response.OrderResponseDTO;
import web.beecommerce.entity.Customer;
import web.beecommerce.entity.Order;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.OrderMapper;
import web.beecommerce.repository.OrderRepository;
import web.beecommerce.service.CustomerService;
import web.beecommerce.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFound("Order not found"));
    }

    @Override
    public OrderResponseDTO findOder(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public OrderResponseDTO save(CreateOrderRequestDTO requestDTO) {
        Customer customer = customerService.findById(requestDTO.getCustomerId());
        if (customer == null) throw new NotFound("Customer not found");
        Order order = orderMapper.createRequestToOrder(requestDTO, customer);
        orderRepository.save(order);
        return orderMapper.mapOrderToOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO update(UpdateOrderRequestDTO requestDTO, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFound("Order not found"));
        orderMapper.updateRequestToOrder(requestDTO, order);
        return orderMapper.mapOrderToOrderResponseDTO(order);
    }
}
