package fi.haagahelia.Bookstore.Bookstore;

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

	// Test create functionalities
	@Test
	public void createNewBook() {
		Book book = new Book("Book Test", "Thien Ly", "123232323-21", 1878, 50.5, new Category("History"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Geography");
		crepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}

	// Test delete functionalities
	@Test
	public void deleteBook() {
		// Add a new Book for testing
		Book book = new Book("Book Test Will Be Deleted", "Thien Ly", "123232323-21", 1878, 50.5, new Category("IT"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();

		// now delete the added book
		repository.delete(book.getId());

		// Verify that the deleted book exists in the repository or not, If false,
		// then it has been deleted
		assertThat(crepository.exists(book.getId())).isFalse();
	}

	@Test
	public void deleteCategory() {
		// Add a new Category for testing
		Category category = new Category("IT");
		crepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();

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
