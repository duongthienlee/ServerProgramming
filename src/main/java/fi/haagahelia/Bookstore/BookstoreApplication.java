package fi.haagahelia.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;
import fi.haagahelia.Bookstore.domain.User;
import fi.haagahelia.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("save books");
			crepository.save(new Category("Novel"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Action and Adventure"));

			// Your code...add some demo data to db
			brepository.save(new Book("Sans Famille", "Hector Malot", "123232323-21", 1878, 50.5,
					crepository.findByName("Novel").get(0)));
			brepository.save(new Book("Oliver Twist", "Charles Dickens", "2212343-5", 1837, 45.5,
					crepository.findByName("Novel").get(0)));
			brepository.save(new Book("The Periodic Table", "Primo Levi", "0-8052-3929-4", 1975, 30.0,
					crepository.findByName("Science").get(0)));
			brepository.save(new Book("Dracula", "Bram Stoker", "235-319-4", 1897, 67.0,
					crepository.findByName("Horror").get(0)));
			brepository.save(new Book("Jurassic Park", "Michael Crichton", "	0-394-58816-9", 1990, 100.0,
					crepository.findByName("Action and Adventure").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"duongthienlee@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"thienwinchester@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
