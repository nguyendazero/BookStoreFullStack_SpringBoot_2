package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.OrderDetail;
import com.bookStoreFullStack.entity.OrderDetailId;
import com.bookStoreFullStack.entity.User;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId>{
	 List<OrderDetail> findByOrderId(int orderId);
	 boolean existsByOrder_UserAndBook_Id(User user, int bookId);
}
