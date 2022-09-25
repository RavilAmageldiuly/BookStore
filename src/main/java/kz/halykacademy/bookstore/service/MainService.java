package kz.halykacademy.bookstore.service;

import java.util.List;

public interface MainService<T, V> {
    List<T> getAll();

    T getIndividual(long id);

    T postIndividual(V v);

    T putIndividual(long id, V v);

    void deleteIndividual(long id);
}
