package ra.service;

import ra.model.dto.request.FilmRequest;
import ra.model.entity.Film;

public interface IFilmService extends IGenericServiceCRUD<Film,Integer>{
    void save(FilmRequest filmRequest);
}
