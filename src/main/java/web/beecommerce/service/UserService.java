package web.beecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateUserRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateUserRequestDTO;
import web.beecommerce.dto.response.UserResponseDTO;
import web.beecommerce.entity.User;

import java.util.List;

@Service
public interface UserService {
    // security of userdetail
    UserDetailsService getUserDetailsService();

    User findById(Long id);

    UserResponseDTO findByUserId(Long id);

    List<User> findAll();

    UserResponseDTO save(CreateUserRequestDTO requestDTO);

    UserResponseDTO update(UpdateUserRequestDTO requestDTO);

    String delete(Long id);

    Page<UserResponseDTO> findAll(Integer pageNo, Integer pageSize);
}
