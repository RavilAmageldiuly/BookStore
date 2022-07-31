package kz.halykacademy.bookstore.dao.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {


    // Add field authorList
    @Modifying
    @Query(value = "update books set price = ?2, title = ?3, publisher_id = ?4, number_of_pages = ?5, release_year = ?6 where book_id = ?1", nativeQuery = true)
    Integer updateBookById(long id, double price, String title, long publisherId, int numberOfPages, int releaseYear);
}
