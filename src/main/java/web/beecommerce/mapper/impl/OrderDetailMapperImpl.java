package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateOrderDetailDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderDetailDTO;
import web.beecommerce.dto.response.OrderDetailResponseDTO;
import web.beecommerce.entity.Order;
import web.beecommerce.entity.OrderDetail;
import web.beecommerce.entity.Product;
import web.beecommerce.mapper.OrderDetailMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetail mapCreateToOrderDetail(CreateOrderDetailDTO request, Order order, Product product) {
        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        return orderDetail;
    }

    @Override
    public OrderDetail mapUpdateToOrderDetail(UpdateOrderDetailDTO request) {
        System.out.println("method mapper mapUpdateToOrderDetail");
        return null;
    }

    @Override
    public OrderDetailResponseDTO mapToOrderDetailResponseDTO(OrderDetail orderDetail) {
        OrderDetailResponseDTO responseDTO = OrderDetailResponseDTO.builder()
                .id(orderDetail.getId())
                .orderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .productName(orderDetail.getProduct().getName())
                .build();
        return responseDTO;
    }

    @Override
    public List<OrderDetailResponseDTO> mapToOrderResponseDTOList(List<OrderDetail> orderDetails) {
        return orderDetails.stream().map(this::mapToOrderDetailResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<OrderDetailResponseDTO> mapToOrderResponseDTOPage(Page<OrderDetail> orderDetails) {
        return orderDetails.map(this::mapToOrderDetailResponseDTO);
    }
}
