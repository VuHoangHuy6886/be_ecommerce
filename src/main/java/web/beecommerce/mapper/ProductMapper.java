package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateProductRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateProductRequestDTO;
import web.beecommerce.dto.response.ProductResponseDTO;
import web.beecommerce.entity.Category;
import web.beecommerce.entity.Product;

import java.util.List;

@Component
public interface ProductMapper {
    // map create request to entity
    Product createRequestToProduct(CreateProductRequestDTO requestDTO, Category category);

    // map update request to entity
    Product updateRequestToProduct(UpdateProductRequestDTO requestDTO, Category category);

    // map category to category response
    ProductResponseDTO mapProductToProductResponseDTO(Product product);

    // map list category to category response
    List<ProductResponseDTO> mapProductListToProductResponseDTOList(List<Product> products);

    // map page category to category response
    Page<ProductResponseDTO> mapProductPageToProductResponseDTOPage(Page<Product> products);
}
