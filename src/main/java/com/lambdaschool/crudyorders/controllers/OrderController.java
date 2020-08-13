package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.services.OrderServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderServices orderServices;

    // http://localhost:2019/orders/order/7
    @GetMapping(value = "order/{id}", produces = "application/json")
    public ResponseEntity<?> findByOrdnum(@PathVariable long id)
    {
        Order myOrder = orderServices.findByOrdnum(id);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }

    // POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Order newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        // return in the header location of the new restaurant
        // location => http://localhost:2019/orders/order/7

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newOrder.getOrdnum())
                .build()
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{ordnum}", consumes = "application/json")
    public ResponseEntity<?> updateFullOrder(@Valid @RequestBody Order updateOrder,
                                             @PathVariable long ordnum)
    {
        updateOrder.setOrdnum(ordnum);
        orderServices.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum)
    {
        orderServices.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
