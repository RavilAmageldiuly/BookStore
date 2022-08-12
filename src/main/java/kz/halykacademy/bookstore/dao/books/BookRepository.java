package kz.halykacademy.bookstore.dao.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> getBookEntitiesByTitleContainingIgnoreCase(String title);
}
