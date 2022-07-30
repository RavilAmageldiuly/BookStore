package kz.halykacademy.bookstore.dao.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {


    // Add field publisherId and then, authorList
    @Modifying
    @Query("update BookEntity b set b.price = ?2, b.title = ?3, b.numberOfPages = ?4, b.releaseYear = ?5 where b.book_id = ?1")
    Integer updateBookById(long id, double price, String title, int numberOfPages, int releaseYear);
}
