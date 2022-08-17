package kz.halykacademy.bookstore.web.genres;


import kz.halykacademy.bookstore.service.genres.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public Page<Genre> findAll(Pageable pageRequest) {
        return new PageImpl<>(
                genreService.getAll()
                        .stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public Genre findOne(@PathVariable Long id) {
        return genreService.getGenre(id);
    }

    @PostMapping
    public Genre postGenre(@RequestBody Genre saveGenre) {
        return genreService.postGenre(saveGenre);
    }

    @PutMapping("/{id}")
    public Genre putGenre(@PathVariable Long id, @RequestBody Genre saveGenre) {
        return genreService.putGenre(id, saveGenre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
