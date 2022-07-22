package kz.halykacademy.bookstore;

import java.util.ArrayList;
import java.util.List;

public class AuthorProviderImpl implements AuthorProvider{
    private static List<Author> allAuthors = new ArrayList<>();

    @Override
    public List<Author> getAll() {
        return allAuthors;
    }
}
