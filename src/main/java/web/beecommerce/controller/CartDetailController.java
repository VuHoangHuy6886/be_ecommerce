package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateCartDetailRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCartDetailRequestDTO;
import web.beecommerce.dto.response.CartDetailResponseDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.service.CartDetailService;

import java.util.List;

@RestController // @Controller
@RequestMapping("/cart-detail")
@RequiredArgsConstructor
public class CartDetailController {
    private final CartDetailService cartDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CreateCartDetailRequestDTO requestDTO) {
        try {
            CartDetailResponseDTO responseDTO = cartDetailService.save(requestDTO);
            return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.CREATED.value(), "Save cartDetail successfully", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateCartDetailRequestDTO requestDTO) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "Id must be a number"));
        }
        try {
            CartDetailResponseDTO responseDTO = cartDetailService.update(requestDTO, idL);
            return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.CREATED.value(), "Update cartDetail successfully", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "Id must be a number"));
        }
        try {
            String message = cartDetailService.delete(idL);
            return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.OK.value(), "Find cartDetail successfully", message, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "Id must be a number"));
        }
        try {
            CartDetailResponseDTO responseDTO = cartDetailService.findCartDetailById(idL);
            return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.OK.value(), "Find cartDetail successfully", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/filterByCartId/{id}")
    public ResponseEntity<?> filterByCartId(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "Id must be a number"));
        }
        try {
            List<CartDetailResponseDTO> responseDTO = cartDetailService.findAllCartDetailByCartId(idL);
            return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.OK.value(), "FindAll cartDetail by cart id successfully", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
