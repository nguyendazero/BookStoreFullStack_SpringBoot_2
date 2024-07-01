package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.OrderEntity;
import com.bookStoreFullStack.repository.OrderEntityRepository;
import com.bookStoreFullStack.service.OrderEntityService;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Override
    public List<OrderEntity> getAllOrderEntityByUserId(int idUser) {
        return orderEntityRepository.findByUserId(idUser);
    }

    @Override
    public OrderEntity saveOrderEntity(OrderEntity orderEntity) {
        return orderEntityRepository.save(orderEntity);
    }

    @Override
    public OrderEntity getOrderEntityById(int id) {
        return orderEntityRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrderEntity(int id) {
        if (orderEntityRepository.existsById(id)) {
            orderEntityRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("OrderEntity with id " + id + " does not exist");
        }
    }
}
