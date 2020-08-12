package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer save(Customer c);

    List<Customer> findAllOrders();

    Customer findCustomerByCode(long c);

    List<Customer> findCustomerByNameLike(String n);
}
