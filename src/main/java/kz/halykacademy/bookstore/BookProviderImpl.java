package kz.halykacademy.bookstore;

import java.util.ArrayList;
import java.util.List;

public class BookProviderImpl implements BookProvider{
    private static List<Book> allBooks = new ArrayList<>();

    @Override
    public List<Book> getAll() {
        return allBooks;
    }
}
