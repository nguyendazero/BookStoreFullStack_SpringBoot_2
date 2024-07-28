package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.OrderDetail;
import com.bookStoreFullStack.entity.OrderEntity;
import com.bookStoreFullStack.entity.User;

public interface OrderEntityService {
    List<OrderEntity> getAllOrderEntityByUserId(int idUser);
    
    OrderEntity saveOrderEntity(OrderEntity orderEntity);
    
    OrderEntity getOrderEntityById(int id);
    
    void deleteOrderEntity(int id);
    
    void saveOrderDetails(List<OrderDetail> orderDetails);
    
    OrderEntity createOrder(User user, List<CartItem> cartItems, String paymentMethod, Integer couponId);
    
}
