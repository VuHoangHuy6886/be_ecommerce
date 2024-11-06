package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateCategoryRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCategoryRequestDTO;
import web.beecommerce.dto.response.CategoryResponseDTO;
import web.beecommerce.entity.Category;
import web.beecommerce.mapper.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category createRequestToCategory(CreateCategoryRequestDTO requestDTO) {
        Category category = new Category();
        category.setName(requestDTO.getName());
        category.setDescription(requestDTO.getDescription());
        category.setStatus(requestDTO.getStatus());
        return category;
    }

    @Override
    public Category updateRequestToCategory(UpdateCategoryRequestDTO requestDTO) {
        Category category = new Category();
        category.setId(requestDTO.getId());
        category.setName(requestDTO.getName());
        category.setDescription(requestDTO.getDescription());
        category.setStatus(requestDTO.getStatus());
        return category;
    }

    @Override
    public CategoryResponseDTO mapCategoryToCategoryResponseDTO(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setDescription(category.getDescription());
        categoryResponseDTO.setStatus(category.getStatus());
        return categoryResponseDTO;
    }

    @Override
    public List<CategoryResponseDTO> mapCategoryListToCategoryResponseDTOList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::mapCategoryToCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoryResponseDTO> mapCategoryToCategoryResponseDTOPage(Page<Category> categories) {
        return categories.map(this::mapCategoryToCategoryResponseDTO);
    }
}
