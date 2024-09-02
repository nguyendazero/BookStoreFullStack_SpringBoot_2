package com.bookStoreFullStack.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.Category;
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
	
	@GetMapping("/user/change-pass-form")
	public String changePassPage(Model model) {
		return "change-pass";
	}
	
	@PostMapping("/user/change-password")
	public String ChangePassword(@RequestParam("currentPassword") String currentPassword,
							     @RequestParam("newPassword") String newPassword,
							     @RequestParam("confirmPassword") String confirmPassword,
							     Model model) {
		String error = "";
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null) {
			return "redirect:/user/login-page";
		}
		if(!currentPassword.equalsIgnoreCase(userLogin.getPassword())) {
			error += "Mật khẩu cũ không chính xác!";
		}else {
			if(!newPassword.equalsIgnoreCase(confirmPassword)) {
				error += "Mật khẩu nhập lại không khớp!";
			}else {
				if(newPassword.equalsIgnoreCase(currentPassword)) {
					error += "Mật khẩu mới không được trùng mật khẩu cũ!";
				}else {
					error += "Đổi mật khẩu thành công!";
					userLogin.setPassword(newPassword);
					userService.saveUser(userLogin);
					return "success";
				}
			}
		}
		model.addAttribute("error", error);
		return "change-pass";
	}
	
	@GetMapping("/user/update-info-form")
	public String updateInforForm(Model model) {
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null) {
			return "redirect:/user/login-page";
		}
		model.addAttribute("user", userLogin);
		return "change-infor";
	}
	
	@PostMapping("user/update")
	public String updateUser(@RequestParam("fullName") String fullName,
							 @RequestParam("gender") String gender,
							 @RequestParam("telephone") String telephone,
							 @RequestParam("address") String address,
							 @RequestParam("dateOfBirth") Date dateOfBirth,
							 @RequestParam("email") String email, Model model) {
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null) {
			return "redirect:/user/login-page";
		}
		
		userLogin.setFullName(fullName);
		userLogin.setGender(gender);
		userLogin.setTelephone(telephone);
		userLogin.setAddress(address);
		userLogin.setEmail(email);
		userLogin.setDateOfBirth(dateOfBirth);
		
		userService.saveUser(userLogin);
		model.addAttribute("error", "Thay đổi thông tin thành công!");
		model.addAttribute("user", userLogin);
		return "success";
	}
	
	/*****************************ADMIN*********************************/
	
	@GetMapping("/admin/user")
	public String ManagerUser(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "admin/user";
	}
	
	@GetMapping("/admin/users/edit-role/{id}")
	public String editRole(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null) {
			return "redirect:/user/login-page";
		}
		
		if(userLogin.getUserName().equals("admin")) {
			User userGet = userService.getUserById(id);
			if(userGet.getRole() == 0) {
				userGet.setRole(1);
				userService.saveUser(userGet);
			}else {
				userGet.setRole(0);
				userService.saveUser(userGet);
			}
		}else {
			redirectAttributes.addFlashAttribute("error", "Bạn không có quyền này!");
		}
		return "redirect:/admin/user";
	}
	
	@GetMapping("/admin/users/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null) {
			return "redirect:/user/login-page";
		}
		
		if(userLogin.getUserName().equals("admin")) {
			userService.deleteUser(id);
		}else {
			redirectAttributes.addFlashAttribute("error", "Bạn không có quyền này!");
		}
		return "redirect:/admin/user";
	}
	

}
