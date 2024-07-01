package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.OrderDetail;
import com.bookStoreFullStack.repository.OrderDetailRepository;
import com.bookStoreFullStack.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetailByIdOrder(int id) {
        return orderDetailRepository.findByOrderId(id);
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail od) {
        return orderDetailRepository.save(od);
    }
}
