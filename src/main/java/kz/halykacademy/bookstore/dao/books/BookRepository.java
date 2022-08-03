package kz.halykacademy.bookstore.dao.books;

import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {


    // make case-insensitive
    @Modifying
    @Query("select b from BookEntity b where b.title like %:title%")
    List<BookEntity> getBooksByTitle(String title);
}
