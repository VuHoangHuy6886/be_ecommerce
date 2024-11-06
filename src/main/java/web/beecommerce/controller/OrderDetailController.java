package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateOrderDetailDTO;
import web.beecommerce.dto.response.OrderDetailResponseDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CreateOrderDetailDTO requestDTO) {
        try {
            OrderDetailResponseDTO responseDTO = orderDetailService.save(requestDTO);
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData<>(HttpStatus.CREATED.value(), "Create Order-detail successfully!",
                            responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findAllByOrderId(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "id must be number"));
        }
        try {
            List<OrderDetailResponseDTO> responseDTOS = orderDetailService.getAllByOrderId(idL);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "findAll Order-detail by id order  successfully!",
                            responseDTOS, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
