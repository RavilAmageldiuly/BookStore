package kz.halykacademy.bookstore.web.genres;


import kz.halykacademy.bookstore.service.genres.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> findAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public Genre findOne(@PathVariable Long id) {
        return genreService.getGenre(id);
    }

    @PostMapping
    public Genre postGenre(@RequestBody SaveGenre saveGenre) {
        return genreService.postGenre(saveGenre);
    }

    @PutMapping("/{id}")
    public Genre putGenre(@PathVariable Long id, @RequestBody SaveGenre saveGenre) {
        return genreService.putGenre(id, saveGenre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
