package kz.halykacademy.bookstore.dao.publishers;

import kz.halykacademy.bookstore.web.publishers.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

    @Modifying
    @Query("update PublisherEntity p set p.name = ?2 where p.publisher_id = ?1")
    Integer updatePublisherById(long id, String name);


    // make case-insensitive
    @Modifying
    @Query("select p from PublisherEntity p where p.name like %:name%")
    List<PublisherEntity> getPublishersByName(String name);
}
