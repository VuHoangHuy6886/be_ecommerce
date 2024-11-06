package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateOrderDetailDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderDetailDTO;
import web.beecommerce.dto.response.OrderDetailResponseDTO;
import web.beecommerce.entity.Order;
import web.beecommerce.entity.OrderDetail;
import web.beecommerce.entity.Product;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.OrderDetailMapper;
import web.beecommerce.repository.OrderDetailRepository;
import web.beecommerce.service.OrderDetailService;
import web.beecommerce.service.OrderService;
import web.beecommerce.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductService productService;
    private final OrderService orderService;

    @Override
    public OrderDetailResponseDTO save(CreateOrderDetailDTO request) {
        // find
        Product product = productService.findById(request.getProductId());
        Order order = orderService.findById(request.getOrderId());
        if (product == null || order == null) {
            throw new NotFound("Product or Order Not Found");
        }
        OrderDetail orderDetail = orderDetailMapper.mapCreateToOrderDetail(request, order, product);
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.mapToOrderDetailResponseDTO(orderDetail);
    }

    @Override
    public OrderDetailResponseDTO update(UpdateOrderDetailDTO request) {
        return null;
    }

    @Override
    public OrderDetailResponseDTO get(Long id) {
        return null;
    }

    @Override
    public List<OrderDetailResponseDTO> getAllByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            throw new NotFound("Order Not Found");
        }
        return orderDetailMapper.mapToOrderResponseDTOList(orderDetails);
    }

    @Override
    public Page<OrderDetailResponseDTO> getAll(Integer page, Integer size) {
        return null;
    }
}
