package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateUserRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateUserRequestDTO;
import web.beecommerce.dto.response.UserResponseDTO;
import web.beecommerce.entity.User;
import web.beecommerce.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User createRequestToUser(CreateUserRequestDTO requestDTO) {
        User user = User.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .phoneNumber(requestDTO.getPhoneNumber())
                .address(requestDTO.getAddress())
                .role(requestDTO.getRole())
                .status(requestDTO.getStatus())
                .build();
        return user;
    }

    @Override
    public User updateRequestToUser(UpdateUserRequestDTO requestDTO) {
        User user = new User();
        user.setId(requestDTO.getId());
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        user.setAddress(requestDTO.getAddress());
        user.setRole(requestDTO.getRole());
        user.setStatus(requestDTO.getStatus());
        return user;
    }

    @Override
    public UserResponseDTO mapUserToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> mapUserListToUserResponseDTOList(List<User> users) {
        return users.stream().map(this::mapUserToUserResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<UserResponseDTO> mapUserPageToUserResponseDTOPage(Page<User> users) {
        return users.map(this::mapUserToUserResponseDTO);
    }
}
