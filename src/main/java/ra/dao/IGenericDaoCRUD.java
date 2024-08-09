package ra.dao;

import java.util.List;

public interface IGenericDaoCRUD <T,E>{
    List<T> findAll();

    T findById(E id);

    void save(T t);

    void delete(E id);
}
