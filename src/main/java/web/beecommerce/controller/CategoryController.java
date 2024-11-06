package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateCategoryRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCategoryRequestDTO;
import web.beecommerce.dto.response.CategoryResponseDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.service.CategoryService;

@Slf4j
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    private ResponseEntity<?> create(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
        try {
            CategoryResponseDTO responseDTO = categoryService.save(createCategoryRequestDTO);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "save category successfully!",
                            responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "id must be number"));
        }
        try {
            CategoryResponseDTO responseDTO = categoryService.findByCategory(idL);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "Find category successfully!",
                            responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> findById(@RequestBody UpdateCategoryRequestDTO requestDTO) {
        try {
            CategoryResponseDTO responseDTO = categoryService.update(requestDTO);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "update category successfully!",
                            responseDTO, null, null, null, null));
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
            String message = categoryService.delete(idL);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "delete category successfully!",
                            message, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll(@RequestParam(required = false) String pageNo,
                                     @RequestParam(required = false) String pageSize) {
        Integer pageIn, sizeIn;
        try {
            pageIn = pageNo == null ? 0 : Integer.parseInt(pageNo);
            sizeIn = pageSize == null ? 10 : Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "pageNo and pageSize must be number"));
        }
        try {
            if (pageIn > 0) {
                pageIn = pageIn - 1;
            }
            Page<CategoryResponseDTO> responseDTO = categoryService.findAll(pageIn, sizeIn);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "findAll category successfully!",
                            responseDTO.getContent(), responseDTO.getNumber(), responseDTO.getSize(), responseDTO.getTotalElements(), responseDTO.getTotalPages()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "findAll category failed!"));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam(required = false) String pageNo,
                                    @RequestParam(required = false) String pageSize,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String status
    ) {
        Integer pageIn, sizeIn;
        try {
            pageIn = pageNo == null ? 0 : Integer.parseInt(pageNo);
            sizeIn = pageSize == null ? 10 : Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "pageNo and pageSize must be number"));
        }
        try {
            if (pageIn > 0) {
                pageIn = pageIn - 1;
            }
            Page<CategoryResponseDTO> responseDTO = categoryService.filter(pageIn, sizeIn, name, status);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "find by filter category successfully!",
                            responseDTO.getContent(), responseDTO.getNumber(), responseDTO.getSize(), responseDTO.getTotalElements(), responseDTO.getTotalPages()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), "find by filter category failed!"));
        }
    }


}
