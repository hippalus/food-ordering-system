package com.hippalus.orderingservice.application.web;

import com.hippalus.orderingservice.application.request.CreateOrderRequest;
import com.hippalus.orderingservice.application.response.OrderResponse;
import com.hippalus.orderingservice.domain.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Log4j2
public class OrderResource {
    private final IOrderService orderService;

    @PostMapping(value = "/orders/create",consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse response =null;
        String err=null;
        try {
            response= orderService.createOrder(request);
        } catch (Exception e) {
            err=e.getMessage();
            log.error(e.getMessage());
        }
        if (err==null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.ok(err);
    }
}