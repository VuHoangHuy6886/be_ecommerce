package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateCategoryRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCategoryRequestDTO;
import web.beecommerce.dto.response.CategoryResponseDTO;
import web.beecommerce.entity.Category;

import java.util.List;

@Component
public interface CategoryMapper {
    // map create request to entity
    Category createRequestToCategory(CreateCategoryRequestDTO requestDTO);

    // map update request to entity
    Category updateRequestToCategory(UpdateCategoryRequestDTO requestDTO);

    // map category to category response
    CategoryResponseDTO mapCategoryToCategoryResponseDTO(Category category);

    // map list category to category response
    List<CategoryResponseDTO> mapCategoryListToCategoryResponseDTOList(List<Category> categoryList);

    // map page category to category response
    Page<CategoryResponseDTO> mapCategoryToCategoryResponseDTOPage(Page<Category> categories);
}
