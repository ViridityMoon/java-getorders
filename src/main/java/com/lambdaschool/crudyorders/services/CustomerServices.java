package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer save(Customer c);

    List<Customer> findAllOrders();

    Customer findCustomerByCode(long c);

    List<Customer> findCustomerByNameLike(String n);

    void delete(long id);

    Customer update(Customer c, long id);
}
