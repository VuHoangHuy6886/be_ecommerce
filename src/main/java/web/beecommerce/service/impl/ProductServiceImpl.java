package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateProductRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateProductRequestDTO;
import web.beecommerce.dto.response.ProductResponseDTO;
import web.beecommerce.entity.Category;
import web.beecommerce.entity.Product;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.ProductMapper;
import web.beecommerce.repository.ProductRepository;
import web.beecommerce.service.CategoryService;
import web.beecommerce.service.ProductService;
import web.beecommerce.util.GenericSpecification;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFound("product not found"));
    }

    @Override
    public ProductResponseDTO findByCategory(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFound("product not found"));
        return productMapper.mapProductToProductResponseDTO(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponseDTO save(CreateProductRequestDTO requestDTO) {
        Category category = categoryService.findById(requestDTO.getCategoryId());
        Product product = productMapper.createRequestToProduct(requestDTO, category);
        productRepository.save(product);
        ProductResponseDTO productResponseDTO = productMapper.mapProductToProductResponseDTO(product);
        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO update(UpdateProductRequestDTO requestDTO) {
        Category category = categoryService.findById(requestDTO.getCategoryId());
        Product product = productMapper.updateRequestToProduct(requestDTO, category);
        productRepository.save(product);

        ProductResponseDTO productResponseDTO = productMapper.mapProductToProductResponseDTO(product);
        return productResponseDTO;
    }

    @Override
    public String delete(Long id) {
        return "";
    }

    @Override
    public Page<ProductResponseDTO> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPages = productRepository.findAll(pageable);
        Page<ProductResponseDTO> pageResponseDTOS = productMapper.mapProductPageToProductResponseDTOPage(productPages);
        return pageResponseDTOS;
    }

    @Override
    public Page<ProductResponseDTO> filter(Integer pageNo,
                                           Integer pageSize,
                                           String codeOrName,
                                           Double price,
                                           Double minPrice,
                                           Double maxPrice,
                                           String status,
                                           Long categoryId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        GenericSpecification<Product> spec = new GenericSpecification<>();
        // Khởi tạo Specification với điều kiện
        Specification<Product> productSpecification = Specification
                .where(codeOrName != null ? spec.hasLike("name", codeOrName).or(spec.hasLike("code", codeOrName)) : null)
                .and(price != null ? spec.hasEqual("price", price) : null)
                .and(maxPrice != null ? spec.hasLessThanOrEqualTo("price", maxPrice) : null)
                .and(minPrice != null ? spec.hasGreaterThanOrEqualTo("price", minPrice) : null)
                .and(categoryId != null ? spec.hasCategoryId("category", categoryId) : null)
                .and(status != null ? spec.hasEqual("status", status) : null);

        Page<Product> productPages = productRepository.findAll(productSpecification, pageable);
        Page<ProductResponseDTO> pageResponseDTOS = productMapper.mapProductPageToProductResponseDTOPage(productPages);
        return pageResponseDTOS;
    }
}
