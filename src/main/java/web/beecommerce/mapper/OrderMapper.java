package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateOrderRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderRequestDTO;
import web.beecommerce.dto.response.OrderResponseDTO;
import web.beecommerce.entity.Customer;
import web.beecommerce.entity.Order;

import java.util.List;

@Component
public interface OrderMapper {
    // map request to entity
    // map create request to entity
    Order createRequestToOrder(CreateOrderRequestDTO requestDTO, Customer customer);

    // map update request to entity
    Order updateRequestToOrder(UpdateOrderRequestDTO requestDTO, Order order);

    // map entity to  response
    OrderResponseDTO mapOrderToOrderResponseDTO(Order order);

    // map list entity to list response
    List<OrderResponseDTO> mapUserListToUserResponseDTOList(List<Order> orders);

    // map page entity to page response
    Page<OrderResponseDTO> mapUserPageToUserResponseDTOPage(Page<Order> orders);
}
