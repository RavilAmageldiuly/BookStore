package kz.halykacademy.bookstore.service.genres;

import kz.halykacademy.bookstore.web.genres.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getGenre(Long id);

    Genre postGenre(Genre saveGenre);

    Genre putGenre(Long id, Genre saveGenre);

    void deleteGenre(Long id);
}
