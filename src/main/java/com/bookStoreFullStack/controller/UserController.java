package com.bookStoreFullStack.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.CartService;
import com.bookStoreFullStack.service.UserService;


import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private CartService cartService;
	
	@GetMapping("/user/login-page")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("user/login")
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
	    User user = userService.getUserByUsernameAndPass(username, password);
	    if (user != null) {
	    	if(user.getRole() != 1) {
	    		session.setAttribute("userLogin", user);
		        return "redirect:/home"; 
	    	}else {
	    		session.setAttribute("userLogin", user);
	    		return "redirect:/home-admin"; 
	    	} 
	    } else {
	        model.addAttribute("error", "Tên người dùng hoặc mật khẩu không đúng!");
	        return "login";
	    }
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		session.removeAttribute("userLogin");
		return "redirect:/home";
	}
	
	@GetMapping("/user/register-page")
	public String registerPage() {
		return "register";
	}
	
	@PostMapping("/user/register")
	public String register(@RequestParam("username") String username,
			@RequestParam("repassword") String repassword,
            @RequestParam("password") String password,
            @RequestParam("address") String address,
            @RequestParam("date_of_birth") Date dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("full_name") String fullName,
            @RequestParam("telephone") String telephone, Model model) {
		
		if(!password.equalsIgnoreCase(repassword)) {
			model.addAttribute("error", "Mật khẩu nhập lại không khớp!");
			return "register";
		}
		List<User> users = userService.getAllUsers();
		boolean tonTai = false;
		for (User user : users) {
			if(username.equalsIgnoreCase(user.getUserName())) {
				model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
				tonTai = true;
				return "register";
			}
		}
		if(tonTai != true) {
			User newUser = new User();
			newUser.setUserName(username);
			newUser.setPassword(password);
			newUser.setFullName(fullName);
			newUser.setAddress(address);
			newUser.setTelephone(telephone);
			newUser.setEmail(email);
			newUser.setDateOfBirth(dateOfBirth);
			newUser.setGender(gender);
			newUser.setRole(0);
			
			userService.saveUser(newUser);
			
			Cart cart = new Cart();
	        cart.setUser(newUser);
	        cartService.saveCart(cart);
			model.addAttribute("error", "Đã đăng ký thành công, hãy đăng nhập!");
		}
		return "login";
	}
	
}
