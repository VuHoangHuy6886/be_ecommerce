package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateCustomerRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCustomerRequestDTO;
import web.beecommerce.dto.response.CustomerResponseDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CreateCustomerRequestDTO requestDTO) {
        try {
            CustomerResponseDTO responseDTO = customerService.save(requestDTO);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "save customer successfully!",
                    responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateCustomerRequestDTO requestDTO) {
        try {
            CustomerResponseDTO responseDTO = customerService.update(requestDTO);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "update Customer successfully!", responseDTO, null, null, null, null));
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
            String message = customerService.delete(idL);
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
            CustomerResponseDTO responseDTO = customerService.findByUserId(id);
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
            Page<CustomerResponseDTO> pageResponseDTO = customerService.findAll(pageIn, sizeIn);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "findAll Customer successfully!",
                    pageResponseDTO.getContent(), pageResponseDTO.getNumber(), pageResponseDTO.getSize(),
                    pageResponseDTO.getTotalElements(), pageResponseDTO.getTotalPages()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
