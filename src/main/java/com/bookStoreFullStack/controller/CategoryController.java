package com.bookStoreFullStack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Category;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	
	//-------ADMIN-------
	@GetMapping("/admin/category")
	public String ManagerCategory(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "admin/category";
	}
	
	@PostMapping("/admin/category/save")
	public String addCategory(@RequestParam("name") String name, Model model) {
		Category newCategoy = new Category();
		newCategoy.setName(name);
		categoryService.saveCategory(newCategoy);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/category";
	}
	
	@PostMapping("/admin/category/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
}
