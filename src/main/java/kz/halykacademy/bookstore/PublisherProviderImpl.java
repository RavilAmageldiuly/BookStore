package kz.halykacademy.bookstore;

import java.util.ArrayList;
import java.util.List;

public class PublisherProviderImpl implements PublisherProvider{
    private static List<Publisher> allPublishers = new ArrayList<>();

    @Override
    public List<Publisher> getAll() {
        return allPublishers;
    }
}
