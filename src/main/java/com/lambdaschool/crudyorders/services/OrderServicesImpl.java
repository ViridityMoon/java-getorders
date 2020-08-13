package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.models.Payment;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import com.lambdaschool.crudyorders.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrdersRepository ordrepos;

    @Autowired
    PaymentsRepository payrepos;

    @Transactional
    @Override
    public Order save(Order o)
    {
        Order newOrder = new Order();

        if (o.getOrdnum() != 0)
        {
            ordrepos.findById(o.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order Number " + o.getOrdnum() + " Not Found"));

            newOrder.setOrdnum(o.getOrdnum());
        }

        newOrder.setOrdamount(o.getOrdamount());
        newOrder.setAdvanceamount(o.getAdvanceamount());
        newOrder.setCustomer(o.getCustomer());

        // many to one
        // with customer

        // many to many

        newOrder.getPayments().clear();
        for (Payment p : o.getPayments())
        {
            Payment newPay = payrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

            newOrder.getPayments().add(newPay);
        }

        return ordrepos.save(o);
    }


    @Override
    public Order findByOrdnum(long num)
    {
        return ordrepos.findById(num)
                .orElseThrow(() -> new EntityNotFoundException("Order Number " + num + " Not Found"));
    }


    @Override
    public void delete(long id)
    {
        if (ordrepos.findById(id).isPresent())
        {
            ordrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Order Number " + id + " Not Found!");
        }
    }
}
