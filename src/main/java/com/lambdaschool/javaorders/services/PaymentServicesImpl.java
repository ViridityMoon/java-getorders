package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service(value = "paymentServices")
public class PaymentServicesImpl implements PaymentServices
{
    @Autowired
    PaymentsRepository payrepos;

    @Override
    public Payment save(Payment p) {
        return payrepos.save(p);
    }
}
