package web.beecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.beecommerce.dto.request.create_request.CreateOrderRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateOrderRequestDTO;
import web.beecommerce.dto.response.OrderResponseDTO;
import web.beecommerce.dto.response.ResponseData;
import web.beecommerce.dto.response.ResponseError;
import web.beecommerce.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CreateOrderRequestDTO requestDTO) {
        try {
            OrderResponseDTO responseDTO = orderService.save(requestDTO);
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData<>(HttpStatus.CREATED.value(), "Create Order successfully!",
                            responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateOrderRequestDTO requestDTO) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "id must be number"));
        }
        try {
            OrderResponseDTO responseDTO = orderService.update(requestDTO, idL);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "Update Order successfully!",
                            responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseError(HttpStatus.BAD_REQUEST.value(), "id must be number"));
        }
        try {
            OrderResponseDTO responseDTO = orderService.findOder(idL);
            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(), "find Order successfully!",
                            responseDTO, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
