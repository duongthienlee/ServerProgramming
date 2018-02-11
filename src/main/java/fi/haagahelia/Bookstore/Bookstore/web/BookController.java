package fi.haagahelia.Bookstore.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping("/add")
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@RequestMapping("/save")
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.delete(bookId);
		return "redirect:../booklist";

	}

	@RequestMapping("/edit{id}")
	public String findBook(@PathVariable("id") Long bookid, Model model) {
		model.addAttribute("book", repository.findOne(bookid));
		return "editBook";

	}

}
