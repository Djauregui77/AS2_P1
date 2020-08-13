package gt.com.controlestudiantes.services;

import java.util.List;
import java.util.Optional;

public interface ICRUD <T> {
    T add(T t);
    T update(T t);
    void delete(String id);
    Optional<T> find(String id);
    List<T> getAll();
}
