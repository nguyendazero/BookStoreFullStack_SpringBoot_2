package com.bookStoreFullStack.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.OrderEntity;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.CartService;
import com.bookStoreFullStack.service.OrderEntityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderEntityController {
	@Autowired
	private OrderEntityService orderEntityService;
	@Autowired
    private CartService cartService;
    @Autowired
    private HttpSession session;
    
    @PostMapping("/order/ProceedtoCheckout")
    public String proceedToCheckout(HttpServletRequest request, Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if(userLogin == null) {
			return "redirect:/user/login-page";
		}
        List<CartItem> cartItems = cartService.viewCart(userLogin).getItems();
        String paymentMethod = request.getParameter("paymentMethod");
        Integer couponId = (Integer) session.getAttribute("couponId"); 

        orderEntityService.createOrder(userLogin, cartItems, paymentMethod, couponId); 
        session.removeAttribute("couponId");
        
        return "thankyou";
    }
    
    @GetMapping("/order/history")
    public String viewOrderHitory(Model model) {
    	User userLogin = (User) session.getAttribute("userLogin");
    	if(userLogin == null) {
			return "redirect:/user/login-page";
		}
    	List<OrderEntity> orders = orderEntityService.getAllOrderEntityByUserId(userLogin.getId());
    	model.addAttribute("orders", orders);
    	return "orders-history";
    }

}
