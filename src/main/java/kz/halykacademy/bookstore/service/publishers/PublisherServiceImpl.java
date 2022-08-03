package kz.halykacademy.bookstore.service.publishers;

import kz.halykacademy.bookstore.dao.publishers.PublisherEntity;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.publishers.Publisher;
import kz.halykacademy.bookstore.web.publishers.SavePublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService{

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
    public Publisher getPublisher(long id) {
        return publisherRepository.findById(id)
                .map(PublisherEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found. Invalid id supplied!"));
    }

    @Transactional
    @Override
    public Publisher putPublisher(long id, SavePublisher publisher) {
        if (!publisherRepository.existsById(id))
            throw new ResourceNotFoundException("Publisher not found. Invalid id supplied!");
        publisherRepository.updatePublisherById(id, publisher.getName());
        return publisherRepository.getReferenceById(id).toDto();
    }

    @Override
    public Publisher postPublisher(SavePublisher savePublisher) {
        PublisherEntity saved = publisherRepository.save(
                new PublisherEntity(
                        savePublisher.getId(),
                        savePublisher.getName(),
                        null
                )
        );
        return saved.toDto();
    }

    @Override
    public void deletePublisher(long id) {
        if (!publisherRepository.existsById(id))
            throw new ResourceNotFoundException("Publisher not found. Invalid id supplied!");
        publisherRepository.deleteById(id);
    }

    @Override
    public List<Publisher> getPublishersByName(String name) {
        List<PublisherEntity> publishers = publisherRepository.getPublishersByName(name);
        return publishers.stream().map(PublisherEntity::toDto).collect(Collectors.toList());
    }
}
