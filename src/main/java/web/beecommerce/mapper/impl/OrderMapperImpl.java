package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateOrderRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderRequestDTO;
import web.beecommerce.dto.response.OrderResponseDTO;
import web.beecommerce.entity.Customer;
import web.beecommerce.entity.Order;
import web.beecommerce.mapper.OrderMapper;

import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order createRequestToOrder(CreateOrderRequestDTO requestDTO, Customer customer) {
        Order order = Order.builder()
                .code(requestDTO.getCode())
                .buyAt(requestDTO.getBuyAt())
                .consigneePhoneNumber(requestDTO.getConsigneePhoneNumber())
                .consigneeName(requestDTO.getConsigneeName())
                .address(requestDTO.getAddress())
                .totalPrice(requestDTO.getTotalPrice())
                .totalQuantity(requestDTO.getTotalQuantity())
                .orderStatus(requestDTO.getOrderStatus())
                .customer(customer)
                .paymentMethod(requestDTO.getPaymentMethod())
                .build();
        return order;
    }

    @Override
    public Order updateRequestToOrder(UpdateOrderRequestDTO requestDTO, Order order) {
        order.setConsigneeName(requestDTO.getConsigneeName());
        order.setConsigneePhoneNumber(requestDTO.getConsigneePhoneNumber());
        order.setPaymentMethod(requestDTO.getPaymentMethod());
        order.setAddress(order.getAddress());
        order.setOrderStatus(requestDTO.getOrderStatus());
        return order;
    }

    @Override
    public OrderResponseDTO mapOrderToOrderResponseDTO(Order order) {
        OrderResponseDTO responseDTO = OrderResponseDTO.builder()
                .id(order.getId())
                .code(order.getCode())
                .totalQuantity(order.getTotalQuantity())
                .totalPrice(order.getTotalPrice())
                .buyAt(order.getBuyAt())
                .consigneePhoneNumber(order.getConsigneePhoneNumber())
                .consigneeName(order.getConsigneeName())
                .paymentMethod(order.getPaymentMethod())
                .createDate(String.valueOf(order.getCreateDate()))
                .updateDate(String.valueOf(order.getUpdateDate()))
                .address(order.getAddress())
                .orderStatus(order.getOrderStatus())
                .customerId(order.getCustomer().getId())
                .build();
        return responseDTO;
    }

    @Override
    public List<OrderResponseDTO> mapUserListToUserResponseDTOList(List<Order> orders) {
        return List.of();
    }

    @Override
    public Page<OrderResponseDTO> mapUserPageToUserResponseDTOPage(Page<Order> orders) {
        return null;
    }
}
