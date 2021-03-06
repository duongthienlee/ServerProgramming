package fi.haagahelia.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;

	@Autowired
	private CategoryRepository crepository;

	// Display books
	@RequestMapping(value = {"/booklist","/"})
	public String books(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "Listpage";
	}

	// Restful service to return all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}

	// Restful service to find book by id
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
		return (Book) brepository.findOne(bookId);
	}

	// Add book
	@RequestMapping(value = "/add")
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addBook";
	}

	// add book then save book.
	@RequestMapping("/save")
	public String saveBook(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}

	// Delete book
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.delete(bookId);
		return "redirect:../booklist";

	}

	// Edit book
	@RequestMapping(value = "/edit{id}")
	public String findBook(@PathVariable("id") Long bookid, Model model) {
		model.addAttribute("book", brepository.findOne(bookid));
		model.addAttribute("categories", crepository.findAll());
		return "editBook";

	}

	// Login
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

}
