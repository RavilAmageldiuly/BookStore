package kz.halykacademy.bookstore.service.publishers;

import kz.halykacademy.bookstore.dao.publishers.PublisherEntity;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.service.MainService;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.publishers.Publisher;
import kz.halykacademy.bookstore.web.publishers.SavePublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements MainService<Publisher, SavePublisher> {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(PublisherEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Publisher getIndividual(long id) {
        return publisherRepository.findById(id)
                .map(PublisherEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found. Invalid id supplied!"));
    }

    @Transactional
    @Override
    public Publisher putIndividual(long id, SavePublisher publisher) {
        if (!publisherRepository.existsById(id))
            throw new ResourceNotFoundException("Publisher not found. Invalid id supplied!");
        publisherRepository.updatePublisherById(id, publisher.getName());
        return publisherRepository.getReferenceById(id).toDto();
    }

    @Override
    public Publisher postIndividual(SavePublisher savePublisher) {
        PublisherEntity saved = publisherRepository.save(
                new PublisherEntity(
                        null,
                        savePublisher.getName(),
                        null
                )
        );
        return saved.toDto();
    }

    @Override
    public void deleteIndividual(long id) {
        if (!publisherRepository.existsById(id))
            throw new ResourceNotFoundException("Publisher not found. Invalid id supplied!");
        publisherRepository.deleteById(id);
    }

    public List<Publisher> getPublishersByName(String name) {
        return publisherRepository.getPublisherEntitiesByNameContainingIgnoreCase(name).stream().map(PublisherEntity::toDto).collect(Collectors.toList());
    }
}
