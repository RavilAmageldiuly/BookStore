package kz.halykacademy.bookstore.service.publishers;

import kz.halykacademy.bookstore.web.publishers.Publisher;
import kz.halykacademy.bookstore.web.publishers.SavePublisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getAll();

    Publisher getPublisher(long id);

    Publisher putPublisher(long id, Publisher publisher);

    Publisher postPublisher(SavePublisher publisher);

    void deletePublisher(long id);
}
