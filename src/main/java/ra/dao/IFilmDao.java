package ra.dao;

import ra.model.entity.Film;

public interface IFilmDao extends IGenericDaoCRUD<Film,Integer>{
    public String getImageById(Integer id);
}
