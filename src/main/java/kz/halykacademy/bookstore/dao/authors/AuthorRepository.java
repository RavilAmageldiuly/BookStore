package kz.halykacademy.bookstore.dao.authors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Modifying
    @Query("update AuthorEntity a set a.firstName = ?2, a.lastName = ?3, a.patronymic = ?4, a.birthday = ?5 where a.author_id = ?1")
    Integer updateAuthorById(long id, String firstName, String lastName, String patronymic, LocalDate birthday);

    List<AuthorEntity> findAllByFirstNameContainingAndLastNameContainingAndPatronymicContainingIgnoreCase(String firstName, String lastName, String patronymic);
}
