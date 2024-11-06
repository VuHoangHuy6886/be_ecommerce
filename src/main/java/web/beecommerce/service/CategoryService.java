package web.beecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateCategoryRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCategoryRequestDTO;
import web.beecommerce.dto.response.CategoryResponseDTO;
import web.beecommerce.entity.Category;

import java.util.List;

@Service
public interface CategoryService {
    Category findById(Long id);

    CategoryResponseDTO findByCategory(Long id);

    List<Category> findAll();

    CategoryResponseDTO save(CreateCategoryRequestDTO requestDTO);

    CategoryResponseDTO update(UpdateCategoryRequestDTO requestDTO);

    String delete(Long  id);

    Page<CategoryResponseDTO> findAll(Integer pageNo, Integer pageSize);

    Page<CategoryResponseDTO> filter(Integer pageNo, Integer pageSize, String name, String status);

}
