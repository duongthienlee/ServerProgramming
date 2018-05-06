package fi.haagahelia.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;

	// Test Add, Delete functionalities
	@Test
	public void TestBookCrud() {
		// Add new book for testing
		Book book = new Book("Book Test", "Thien Ly", "123232323-21", 1878, 50.5, new Category("History"));
		repository.save(book);

		// assert that the added book is saved to the Book repository
		assertThat(repository.findOne(book.getId())).isNotNull();

		// now delete the added book
		repository.delete(book.getId());

		// Verify that the deleted book exists in the repository or not, If false,
		// then it has been deleted
		assertThat(repository.exists(book.getId())).isFalse();
	}

	// Test Add, Delete functionalities
	@Test
	public void TestCategoryCrud() {
		// Add a new Category for testing
		Category category = new Category("IT");
		crepository.save(category);

		// assert that the added category is in the Category repository
		assertThat(crepository.findOne(category.getCategoryid())).isNotNull();

		// now delete the added book out of crepository
		crepository.delete(category.getCategoryid());

		// Verify that the deleted category exists in the crepository or not, If false,
		// then it has been deleted
		assertThat(crepository.exists(category.getCategoryid())).isFalse();
	}

	// Test search functionalities
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> book = repository.findByTitle("Sans Famille");
		assertThat(book).hasSize(1);
		assertThat(book.get(0).getAuthor()).isEqualTo("Hector Malot");
	}

	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> category = crepository.findByName("Novel");
		assertThat(category).hasSize(1);
	}

}
