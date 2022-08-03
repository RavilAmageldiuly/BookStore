package kz.halykacademy.bookstore.dao.genres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
