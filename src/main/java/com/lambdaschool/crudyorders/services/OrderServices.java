package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;

public interface OrderServices
{
    Order save(Order o);

    Order findByOrdnum(long ordnum);

    void delete(long ordnum);


}