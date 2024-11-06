package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateUserRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateUserRequestDTO;
import web.beecommerce.dto.response.UserResponseDTO;
import web.beecommerce.entity.User;

import java.util.List;

@Component
public interface UserMapper {
    // map create request to entity
    User createRequestToUser(CreateUserRequestDTO requestDTO);

    // map update request to entity
    User updateRequestToUser(UpdateUserRequestDTO requestDTO);

    // map entity to  response
    UserResponseDTO mapUserToUserResponseDTO(User user);

    // map list entity to list response
    List<UserResponseDTO> mapUserListToUserResponseDTOList(List<User> users);

    // map page entity to page response
    Page<UserResponseDTO> mapUserPageToUserResponseDTOPage(Page<User> users);
}
