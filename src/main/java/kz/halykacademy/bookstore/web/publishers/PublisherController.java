package kz.halykacademy.bookstore.web.publishers;


import kz.halykacademy.bookstore.service.publishers.PublisherServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherServiceImpl publisherService;

    public PublisherController(PublisherServiceImpl publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> findAll() {
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public Publisher findOne(@PathVariable Long id) {
        return publisherService.getPublisher(id);
    }

    @PostMapping()
    public Publisher savePublisher(@RequestBody SavePublisher publisher) {
        return publisherService.postPublisher(publisher);
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable Long id, @RequestBody SavePublisher publisher) {
        return publisherService.putPublisher(id, publisher);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }

    @GetMapping("/getByName")
    public List<Publisher> getAllPublishersByName(@RequestParam(value = "name") String name) {
        return publisherService.getPublishersByName(name);
    }
}
