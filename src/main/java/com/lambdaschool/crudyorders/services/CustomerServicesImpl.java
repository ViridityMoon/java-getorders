package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    CustomersRepository custrepos;


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


    @Transactional
    @Override
    public Customer save(Customer c)
    {
        Customer newCustomer = new Customer();

        if (c.getCustcode() != 0)
        {
            custrepos.findById(c.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + c.getCustcode() + " Not Found!"));

            newCustomer.setCustcode(c.getCustcode());
        }

        newCustomer.setCustname(c.getCustname());
        newCustomer.setCustcity(c.getCustcity());
        newCustomer.setCustcountry(c.getCustcountry());
        newCustomer.setWorkingarea(c.getWorkingarea());
        newCustomer.setGrade(c.getGrade());
        newCustomer.setOpeningamt(c.getOpeningamt());
        newCustomer.setReceiveamt(c.getReceiveamt());
        newCustomer.setPaymentamt(c.getPaymentamt());
        newCustomer.setOutstandingamt(c.getOpeningamt());
        newCustomer.setPhone(c.getPhone());

        // many to one
        // agent

        // one to many

        newCustomer.getOrders().clear();
        for (Order o : c.getOrders())
        {
            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setCustomer(o.getCustomer());

            newCustomer.getOrders().add(newOrder);
        }

        return custrepos.save(c);
    }

    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }

    @Override
    public Customer update(Customer c, long id)
    {
        Customer currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        if (c.getCustname() != null)
        {
            currentCustomer.setCustname(c.getCustname());
        }
        if (c.getCustcity() != null)
        {
            currentCustomer.setCustcountry(c.getCustcountry());
        }
        if (c.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(c.getWorkingarea());
        }
        if (c.getGrade() != null)
        {
            currentCustomer.setOpeningamt(c.getOpeningamt());
        }
        if (c.getOpeningamt() > 0)
        {
            currentCustomer.setOutstandingamt(c.getOutstandingamt());
        }
        if (c.getReceiveamt() > 0)
        {
            currentCustomer.setReceiveamt(c.getReceiveamt());
        }
        if (c.getPaymentamt() > 0)
        {
            currentCustomer.setPaymentamt(c.getPaymentamt());
        }
        if (c.getOutstandingamt() > 0)
        {
            currentCustomer.setOutstandingamt(c.getOutstandingamt());
        }
        if (c.getPhone() != null)
        {
            currentCustomer.setPhone(c.getPhone());
        }

        // many to one
        // with agent

        // one to many
        // with orders

        if (c.getOrders().size() > 0)
        {
            currentCustomer.getOrders()
                    .clear();
            for (Order o : c.getOrders())
            {
                Order newOrder = new Order();
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setCustomer(currentCustomer);
                currentCustomer.getOrders()
                        .add(newOrder);
            }
        }

        return custrepos.save(currentCustomer);
    }
}
