package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateProductRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateProductRequestDTO;
import web.beecommerce.dto.response.ProductResponseDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    private ResponseEntity<?> create(@RequestBody CreateProductRequestDTO requestDTO) {
        try {
            ProductResponseDTO responseDTO = productService.save(requestDTO);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "save product successfully!", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("/update")
    private ResponseEntity<?> udpate(@RequestBody UpdateProductRequestDTO requestDTO) {
        try {
            ProductResponseDTO responseDTO = productService.update(requestDTO);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "update product successfully!", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> get(@PathVariable Long id) {
        try {
            ProductResponseDTO responseDTO = productService.findByCategory(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find product successfully!", responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
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
            Page<ProductResponseDTO> pageResponseDTO = productService.findAll(pageIn, sizeIn);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find product successfully!", pageResponseDTO.getContent(), pageResponseDTO.getNumber(), pageResponseDTO.getSize(), pageResponseDTO.getTotalElements(), pageResponseDTO.getTotalPages()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterAll(@RequestParam(required = false) Integer pageNo,
                                       @RequestParam(required = false) Integer pageSize,
                                       @RequestParam(required = false) String codeOrName,
                                       @RequestParam(required = false) Double price,
                                       @RequestParam(required = false) Double minPrice,
                                       @RequestParam(required = false) Double maxPrice,
                                       @RequestParam(required = false) String status,
                                       @RequestParam(required = false) Long categoryId) {

        // Xử lý pageNo và pageSize mặc định
        int pageIn = (pageNo == null) ? 0 : pageNo;
        int sizeIn = (pageSize == null) ? 10 : pageSize;

        try {
            if (pageIn > 0) {
                pageIn = pageIn - 1;
            }
            // Gọi hàm filter với các tham số lọc
            Page<ProductResponseDTO> pageResponseDTO = productService.filter(pageIn, sizeIn, codeOrName, price, minPrice, maxPrice, status, categoryId);

            // Trả về kết quả
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Filter products successfully!", pageResponseDTO.getContent(), pageResponseDTO.getNumber(), pageResponseDTO.getSize(), pageResponseDTO.getTotalElements(), pageResponseDTO.getTotalPages()));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "pageNo and pageSize must be numbers"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

}
