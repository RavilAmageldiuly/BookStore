package kz.halykacademy.bookstore.dao.books;

import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {


}
