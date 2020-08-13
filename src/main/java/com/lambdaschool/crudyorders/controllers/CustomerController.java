package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    // POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);

        // return in the header location of the new customer
        // location => http://localhost:2019/customers/customer/10

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newCustomer.getCustcode())
                .build()
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updateCustomer,
                                                @PathVariable long custcode)
    {
        updateCustomer.setCustcode(custcode);
        customerServices.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer,
                                            @PathVariable long custcode)
    {
        customerServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customerServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
