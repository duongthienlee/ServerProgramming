package fi.haagahelia.Bookstore.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			// Your code...add some demo data to db
			Book b1 = new Book("Sans Famille", "Hector Malot", 1878, 50.5);
			Book b2 = new Book("Oliver Twist", "Charles Dickens", 1837, 45.5);

			repository.save(b1);
			repository.save(b2);
		};
	}
}
