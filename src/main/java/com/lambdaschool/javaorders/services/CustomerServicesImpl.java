package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    CustomersRepository custrepos;

    @Override
    public Customer save(Customer c)
    {
        return custrepos.save(c);
    }

    @Override
    public List<Customer> findAllOrders()
    {
        List<Customer> list = new ArrayList<>();

        custrepos.findAll().iterator().forEachRemaining(list :: add);
        return list;
    }

    @Override
    public Customer findCustomerByCode(long custcode) {
        return custrepos.findById(custcode)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + custcode + " Not Available!"));
    }

    @Override
    public List<Customer> findCustomerByNameLike(String n) {
        return custrepos.findByCustnameContainingIgnoringCase(n);
    }
}
