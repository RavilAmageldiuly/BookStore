package kz.halykacademy.bookstore.service.genres;

import kz.halykacademy.bookstore.dao.genres.GenreEntity;
import kz.halykacademy.bookstore.dao.genres.GenreRepository;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.genres.Genre;
import kz.halykacademy.bookstore.web.genres.SaveGenre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(GenreEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Genre getGenre(Long id) {
        return genreRepository.findById(id)
                .map(GenreEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found. Invalid id Supplied!"));
    }

    @Override
    public Genre postGenre(SaveGenre saveGenre) {
        return genreRepository.save(
                new GenreEntity(
                        saveGenre.getGenreId(),
                        saveGenre.getGenreName()
                )
        ).toDto();
    }

    @Override
    public Genre putGenre(Long id, SaveGenre saveGenre) {
        if (!genreRepository.existsById(id))
            throw new ResourceNotFoundException("Genre not found. Invalid id Supplied!");
        return genreRepository.save(
                new GenreEntity(
                        id,
                        saveGenre.getGenreName()
                )
        ).toDto();
    }

    @Override
    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id))
            throw new ResourceNotFoundException("Genre not found. Invalid id Supplied!");

        genreRepository.deleteById(id);
    }
}
