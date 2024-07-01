package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.OrderDetail;

public interface OrderDetailService {
	List<OrderDetail> getAllOrderDetailByIdOrder(int id);
	
	OrderDetail saveOrderDetail(OrderDetail od);
	
}
