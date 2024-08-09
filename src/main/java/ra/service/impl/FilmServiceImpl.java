package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.impl.ActorDaoImpl;
import ra.dao.impl.FilmDaoImpl;
import ra.model.dto.request.FilmRequest;
import ra.model.entity.Actor;
import ra.model.entity.Film;
import ra.service.IFilmService;
import ra.service.UploadService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements IFilmService {
    @Autowired
    private FilmDaoImpl filmDao;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ActorDaoImpl actorDao;

    @Override
    public List<Film> findAll() {
        return filmDao.findAll();
    }

    @Override
    public Film findById(Integer id) {
        return filmDao.findById(id);
    }

    @Override
    public void save(FilmRequest filmRequest) {
//        Set<Actor> actors = new HashSet<>();

//        for (Actor actor : filmRequest.getActorList()) {
//            Actor persistentActor = actorDao.findById(actor.getId());
//            actors.add(persistentActor);
//        }

        Set<Actor> actorSet = filmRequest.getActorsId().stream().map(item -> actorDao.findById(item)).collect(Collectors.toSet());

        Film film = Film.builder()
                .id(filmRequest.getId())
                .name(filmRequest.getName())
                .description(filmRequest.getDescription())
                .trailerUrl(filmRequest.getTrailerUrl())
                .isFree(filmRequest.getIsFree())
                .releaseDate(filmRequest.getReleaseDate())
                .category(filmRequest.getCategory())
                .type(filmRequest.getType())
                .actorList(actorSet)
                .build();
        if (filmRequest.getId() == null) {
            film.setImage(uploadService.uploadFileToServer(filmRequest.getMultipartFile()));
        } else {
            if (filmRequest.getMultipartFile() != null && filmRequest.getMultipartFile().getSize() > 0) {
                film.setImage(uploadService.uploadFileToServer(filmRequest.getMultipartFile()));
            } else {
                film.setImage(filmDao.getImageById(filmRequest.getId()));
            }
        }
        filmDao.save(film);
    }

    @Override
    public void delete(Integer id) {
        filmDao.delete(id);
    }

}
