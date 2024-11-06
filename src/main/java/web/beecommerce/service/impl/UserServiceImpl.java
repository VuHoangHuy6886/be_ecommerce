package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateUserRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateUserRequestDTO;
import web.beecommerce.dto.response.UserResponseDTO;
import web.beecommerce.entity.User;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.UserMapper;
import web.beecommerce.repository.UserRepository;
import web.beecommerce.service.UserService;
import web.beecommerce.util.BaseStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetailsService getUserDetailsService() {
        log.info("getUserDetailsService");
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new NotFound("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFound("User not found"));
    }

    @Override
    public UserResponseDTO findByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFound("User not found"));
        return userMapper.mapUserToUserResponseDTO(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDTO save(CreateUserRequestDTO requestDTO) {
        User user = userMapper.createRequestToUser(requestDTO);
        userRepository.save(user);
        log.info("create user successfully");
        return userMapper.mapUserToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO update(UpdateUserRequestDTO requestDTO) {
        User user = userMapper.updateRequestToUser(requestDTO);
        userRepository.save(user);
        return userMapper.mapUserToUserResponseDTO(user);
    }

    @Override
    public String delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFound("User not found"));
        user.setStatus(BaseStatus.INACTIVE.getValue());
        userRepository.save(user);
        return "Delete user id : " + id + " successfully";
    }

    @Override
    public Page<UserResponseDTO> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        Page<UserResponseDTO> pageResponseDTOS = userMapper.mapUserPageToUserResponseDTOPage(users);
        return pageResponseDTOS;
    }
}
