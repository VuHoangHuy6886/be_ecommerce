package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateProductRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateProductRequestDTO;
import web.beecommerce.dto.response.ProductResponseDTO;
import web.beecommerce.entity.Category;
import web.beecommerce.entity.Product;
import web.beecommerce.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product createRequestToProduct(CreateProductRequestDTO requestDTO, Category category) {
        Product product = Product.builder()
                .code(requestDTO.getCode())
                .name(requestDTO.getName())
                .quantity(requestDTO.getQuantity())
                .price(requestDTO.getPrice())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .status(requestDTO.getStatus())
                .category(category)
                .build();
        return product;
    }

    @Override
    public Product updateRequestToProduct(UpdateProductRequestDTO requestDTO, Category category) {
        Product product = new Product();
        product.setId(requestDTO.getId());
        product.setCode(requestDTO.getCode());
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        product.setDescription(requestDTO.getDescription());
        product.setImage(requestDTO.getImage());
        product.setStatus(requestDTO.getStatus());
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductResponseDTO mapProductToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setCode(product.getCode());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setQuantity(product.getQuantity());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStatus(product.getStatus());
        productResponseDTO.setImage(product.getImage());
        productResponseDTO.setCategoryId(product.getCategory().getId());
        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDTO> mapProductListToProductResponseDTOList(List<Product> products) {
        return products.stream()
                .map(this::mapProductToProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponseDTO> mapProductPageToProductResponseDTOPage(Page<Product> products) {
        return products.map(this::mapProductToProductResponseDTO);
    }
}
