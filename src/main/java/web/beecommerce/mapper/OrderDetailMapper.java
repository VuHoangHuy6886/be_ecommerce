package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateOrderDetailDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderDetailDTO;
import web.beecommerce.dto.response.OrderDetailResponseDTO;
import web.beecommerce.entity.Order;
import web.beecommerce.entity.OrderDetail;
import web.beecommerce.entity.Product;

import java.util.List;

@Component
public interface OrderDetailMapper {

    OrderDetail mapCreateToOrderDetail(CreateOrderDetailDTO request, Order order, Product product);

    OrderDetail mapUpdateToOrderDetail(UpdateOrderDetailDTO request);

    OrderDetailResponseDTO mapToOrderDetailResponseDTO(OrderDetail orderDetail);

    List<OrderDetailResponseDTO> mapToOrderResponseDTOList(List<OrderDetail> orderDetails);

    Page<OrderDetailResponseDTO> mapToOrderResponseDTOPage(Page<OrderDetail> orderDetails);
}
