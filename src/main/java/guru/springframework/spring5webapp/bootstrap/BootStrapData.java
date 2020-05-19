package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book bbb = new Book("Domain Driven Design", "345454");
        eric.getBooks().add(bbb);
        bbb.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(bbb);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "43534545");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher p = new Publisher("Wiley and Sons", "aa", "bb", "cc", "dd");


        bbb.setPublisher(p);
        p.getBooks().add(bbb);
        noEJB.setPublisher(p);
        p.getBooks().add(noEJB);
        publisherRepository.save(p);
        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books by publisher: " + p.getBooks().size());
    }
}
