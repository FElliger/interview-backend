package de.bringmeister.repository.inmemory;

import java.util.stream.Stream;

public interface DataProvider<T> {

    Stream<T> getAllData();

}
