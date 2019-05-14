package bookStore.studyExample.bookManager.repository;

import bookStore.studyExample.bookManager.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BooksRepository  extends CrudRepository<Book, Integer> {

    List<Book> findByTitle(String title);
    Iterable<Book> findAll();
}