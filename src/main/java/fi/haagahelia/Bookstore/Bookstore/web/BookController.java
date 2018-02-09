package fi.haagahelia.Bookstore.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.Bookstore.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	BookRepository repository;

	@RequestMapping("/booklist")
	public String books(Model model) {
		model.addAttribute("books", repository.findAll());
		return "Listpage";
	}

	@RequestMapping("/create")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@RequestMapping("/save")
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

}
