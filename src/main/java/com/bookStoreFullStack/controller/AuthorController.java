package com.bookStoreFullStack.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Author;
import com.bookStoreFullStack.service.AuthorService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	
	
	//--------ADMIN-------
	@GetMapping("/admin/author")
	public String ManagerAuthor(Model model) {
		List<Author> authors = authorService.getAllAuthors();
		model.addAttribute("authors", authors);
		
		return "admin/author";
	}
	
	@PostMapping("/admin/author/save")
	public String addAuthor(@RequestParam("fullName") String fullName,
							@RequestParam("homeTown") String homeTown,
							@RequestParam("yearOfBirth") int yearOfBirth,
							@RequestParam("story") String story) {
		Author newAuthor = new Author();
		newAuthor.setFullName(fullName);
		newAuthor.setHomeTown(homeTown);
		newAuthor.setStory(story);
		newAuthor.setYearOfBirth(yearOfBirth);
		
		authorService.saveAuthor(newAuthor);
		return "redirect:/admin/author";
	}
	
	@PostMapping("/admin/author/update")
	public String updateAuthor(@ModelAttribute("author") Author author) {
	    authorService.saveAuthor(author);
	    return "redirect:/admin/author";
	}
	
	@GetMapping("/admin/author/delete/{id}")
	public String deleteAuthor(@PathVariable("id") int id) {
		authorService.deleteAuthor(id);
		return "redirect:/admin/author";
	}
}
