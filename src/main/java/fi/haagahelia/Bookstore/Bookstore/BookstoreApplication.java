package fi.haagahelia.Bookstore.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.Bookstore.domain.CategoryRepository;
import fi.haagahelia.Bookstore.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository srepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save books");
			crepository.save(new Category("Novel"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Action and Adventure"));
			
			// Your code...add some demo data to db
			srepository.save(new Book("Sans Famille", "Hector Malot", "123232323-21", 1878, 50.5,
					crepository.findByName("Novel").get(0)));
			srepository.save(new Book("Oliver Twist", "Charles Dickens", "2212343-5", 1837, 45.5,
					crepository.findByName("Novel").get(0)));
			srepository.save(new Book("The Periodic Table", "Primo Levi", "0-8052-3929-4", 1975, 30.0,
					crepository.findByName("Science").get(0)));
			srepository.save(new Book("Dracula", "Bram Stoker", "235-319-4", 1897, 67.0,
					crepository.findByName("Horror").get(0)));
			srepository.save(new Book("Jurassic Park", "Michael Crichton", "	0-394-58816-9", 1990, 100.0,
					crepository.findByName("Action and Adventure").get(0)));

			log.info("fetch all books");
			for (Book book : srepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
