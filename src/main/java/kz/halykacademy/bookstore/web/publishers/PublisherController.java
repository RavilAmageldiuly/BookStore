package kz.halykacademy.bookstore.web.publishers;


import kz.halykacademy.bookstore.service.publishers.PublisherServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherServiceImpl publisherService;

    public PublisherController(PublisherServiceImpl publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public Page<Publisher> findAll(Pageable pageRequest) {
        return new PageImpl(
                publisherService.getAll().stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public Publisher findOne(@PathVariable Long id) {
        return publisherService.getIndividual(id);
    }

    @PostMapping()
    public Publisher savePublisher(@RequestBody SavePublisher publisher) {
        return publisherService.postIndividual(publisher);
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable Long id, @RequestBody SavePublisher publisher) {
        return publisherService.putIndividual(id, publisher);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deleteIndividual(id);
    }

    @GetMapping("/findByName")
    public Page<Publisher> getAllPublishersByName(Pageable pageRequest, @RequestParam(value = "name") String name) {
        return new PageImpl<>(
                publisherService.getPublishersByName(name)
                        .stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }
}
