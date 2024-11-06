package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateCategoryRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCategoryRequestDTO;
import web.beecommerce.dto.response.CategoryResponseDTO;
import web.beecommerce.entity.Category;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.CategoryMapper;
import web.beecommerce.repository.CategoryRepository;
import web.beecommerce.service.CategoryService;
import web.beecommerce.util.GenericSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFound("user not found"));
    }

    @Override
    public CategoryResponseDTO findByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFound("user not found"));
        CategoryResponseDTO categoryResponseDTO = categoryMapper.mapCategoryToCategoryResponseDTO(category);
        return categoryResponseDTO;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryResponseDTO save(CreateCategoryRequestDTO requestDTO) {
        boolean exists = categoryRepository.existsByName(requestDTO.getName());
        if (exists) {
            throw new NotFound("userName already exists");
        }
        Category category = categoryMapper.createRequestToCategory(requestDTO);
        categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = categoryMapper.mapCategoryToCategoryResponseDTO(category);
        return categoryResponseDTO;
    }

    @Override
    public CategoryResponseDTO update(UpdateCategoryRequestDTO requestDTO) {
        boolean exists = categoryRepository.existsByName(requestDTO.getName());
        if (exists) {
            throw new NotFound("userName already exists");
        }
        Category category = categoryMapper.updateRequestToCategory(requestDTO);
        categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = categoryMapper.mapCategoryToCategoryResponseDTO(category);
        return categoryResponseDTO;
    }

    @Override
    public String delete(Long id) {
        return "delete successfully";
    }

    @Override
    public Page<CategoryResponseDTO> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        Page<CategoryResponseDTO> categoryResponseDTOPage = categoryMapper.mapCategoryToCategoryResponseDTOPage(categoryPage);
        return categoryResponseDTOPage;
    }

    @Override
    public Page<CategoryResponseDTO> filter(Integer pageNo, Integer pageSize, String name, String status) {
        GenericSpecification<Category> spec = new GenericSpecification<Category>();
        // dieu kien loc
        Specification<Category> categorySpecification = Specification
                .where(spec.hasLike("name", name))
                .and(spec.hasLike("status", status));
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Category> categoryPage = categoryRepository.findAll(categorySpecification, pageable);
        Page<CategoryResponseDTO> categoryResponseDTOPage = categoryMapper.mapCategoryToCategoryResponseDTOPage(categoryPage);
        return categoryResponseDTOPage;
    }
}
