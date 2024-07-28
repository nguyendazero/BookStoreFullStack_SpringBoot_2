package com.bookStoreFullStack.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.Coupon;
import com.bookStoreFullStack.entity.OrderDetail;
import com.bookStoreFullStack.entity.OrderDetailId;
import com.bookStoreFullStack.entity.OrderEntity;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.repository.CouponRepository;
import com.bookStoreFullStack.repository.OrderDetailRepository;
import com.bookStoreFullStack.repository.OrderEntityRepository;
import com.bookStoreFullStack.service.CartItemService;
import com.bookStoreFullStack.service.CartService;
import com.bookStoreFullStack.service.OrderEntityService;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    @Autowired
    private OrderEntityRepository orderEntityRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private HttpSession session;
    @Autowired
    private CartItemService cartItemService;
    

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
    
    @Override
    public void saveOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }

	@Override
	public OrderEntity createOrder(User user, List<CartItem> cartItems, String paymentMethod, Integer couponId) {
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setDate(new Date(System.currentTimeMillis()));
        order.setPaymentMethod(paymentMethod);
        order.setStatus("Pending");

        double total = cartItems.stream()
                                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                                .sum();
        order.setTotal(total);

        if (couponId != null) {
            Coupon coupon = couponRepository.findById(couponId)
                                            .orElseThrow(() -> new IllegalArgumentException("Invalid coupon ID"));
            order.setCoupon(coupon);
            double discountedTotal = total - (total * coupon.getDiscount()) / 100;
            order.setTotal(discountedTotal);
        }

        OrderEntity savedOrder = orderEntityRepository.save(order);

        // Create and save order details
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            OrderDetailId orderDetailId = new OrderDetailId();
            
            orderDetailId.setOrderId(savedOrder.getId());
            orderDetailId.setBookId(cartItem.getBook().getId());
            
            orderDetail.setId(orderDetailId);
            orderDetail.setOrder(savedOrder);
            orderDetail.setBook(cartItem.getBook());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
            orderDetails.add(orderDetail);
        }
        orderDetailRepository.saveAll(orderDetails);

        cartService.clearCartItems(user);
        return savedOrder;
    }


}
