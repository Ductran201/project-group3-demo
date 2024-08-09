package ra.service;

import java.util.List;

public interface IGenericServiceCRUD<T, E> {
    List<T> findAll();

    T findById(E id);

    void delete(E id);
}
