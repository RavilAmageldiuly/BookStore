package kz.halykacademy.bookstore.service.genres;

import kz.halykacademy.bookstore.web.genres.Genre;
import kz.halykacademy.bookstore.web.genres.SaveGenre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getGenre(Long id);

    Genre postGenre(SaveGenre saveGenre);

    Genre putGenre(Long id, SaveGenre saveGenre);

    void deleteGenre(Long id);
}
