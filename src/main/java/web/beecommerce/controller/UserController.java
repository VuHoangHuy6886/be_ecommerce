package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateUserRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateUserRequestDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.dto.response.UserResponseDTO;
import web.beecommerce.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CreateUserRequestDTO requestDTO) {
        try {
            UserResponseDTO responseDTO = userService.save(requestDTO);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "save User successfully!",
                    responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequestDTO requestDTO) {
        try {
            UserResponseDTO responseDTO = userService.update(requestDTO);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "update User successfully!", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "id must be number"));
        }
        try {
            String message = userService.delete(idL);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                    "Delete User Successfully!",
                    message, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            UserResponseDTO responseDTO = userService.findByUserId(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                    "find User Successfully!",
                    responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/list")
    private ResponseEntity<?> getAll(@RequestParam(required = false) String pageNo, @RequestParam(required = false) String pageSize) {
        Integer pageIn, sizeIn;
        try {
            pageIn = pageNo == null ? 0 : Integer.parseInt(pageNo);
            sizeIn = pageSize == null ? 10 : Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "pageNo and pageSize must be number"));
        }
        try {
            if (pageIn > 0) {
                pageIn = pageIn - 1;
            }
            Page<UserResponseDTO> pageResponseDTO = userService.findAll(pageIn, sizeIn);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find User successfully!",
                    pageResponseDTO.getContent(), pageResponseDTO.getNumber(), pageResponseDTO.getSize(),
                    pageResponseDTO.getTotalElements(), pageResponseDTO.getTotalPages()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

}
