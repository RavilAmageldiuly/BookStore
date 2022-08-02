package kz.halykacademy.bookstore.service.publishers;

import kz.halykacademy.bookstore.web.publishers.Publisher;
import kz.halykacademy.bookstore.web.publishers.SavePublisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getAll();

    Publisher getPublisher(long id);

    Publisher putPublisher(long id, SavePublisher publisher);

    Publisher postPublisher(SavePublisher savePublisher);

    void deletePublisher(long id);

    List<Publisher> getPublishersByName(String name);
}
