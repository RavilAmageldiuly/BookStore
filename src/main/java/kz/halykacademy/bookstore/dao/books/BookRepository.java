package kz.halykacademy.bookstore.dao.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> getBookEntitiesByTitleContainingIgnoreCase(String title);
}
