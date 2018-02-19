package fi.haagahelia.Bookstore.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.Bookstore.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;
	
	//Display books
	@RequestMapping("/booklist")
	public String books(Model model) {
		model.addAttribute("books", repository.findAll());
		return "Listpage";
	}

	//Add book
	@RequestMapping("/add")
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addBook";
	}
 // add book then save book.
	@RequestMapping("/save")
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	//Delete book
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.delete(bookId);
		return "redirect:../booklist";

	}
	
	//Edit book
	@RequestMapping("/edit{id}")
	public String findBook(@PathVariable("id") Long bookid, Model model) {
		model.addAttribute("book", repository.findOne(bookid));
		return "editBook";

	}

}
