package web.beecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateProductRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateProductRequestDTO;
import web.beecommerce.dto.response.ProductResponseDTO;
import web.beecommerce.entity.Product;

import java.util.List;

@Service
public interface ProductService {
    Product findById(Long id);

    ProductResponseDTO findByCategory(Long id);

    List<Product> findAll();

    ProductResponseDTO save(CreateProductRequestDTO requestDTO);

    ProductResponseDTO update(UpdateProductRequestDTO requestDTO);

    String delete(Long id);

    Page<ProductResponseDTO> findAll(Integer pageNo, Integer pageSize);

    Page<ProductResponseDTO> filter(Integer pageNo, Integer pageSize, String codeOrName, Double price, Double minPrice, Double maxPrice, String status, Long categoryId);

}
