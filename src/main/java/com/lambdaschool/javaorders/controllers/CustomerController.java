package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerServices;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerServices customerServices;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders()
    {
        List<Customer> myList = customerServices.findAllOrders();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{c}", produces = "application/json")
    public ResponseEntity<?> findCustomerByCode(@PathVariable long c)
    {
        Customer myCustomer = customerServices.findCustomerByCode(c);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/77
    // Shoots Error Properly!

    // http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{n}", produces = "application/json")
    public ResponseEntity<?> findCustomerByNameLike(@PathVariable String n)
    {
        List<Customer> myList = customerServices.findCustomerByNameLike(n);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/cin
    // []


    // http://localhost:2019/orders/order/7
}
