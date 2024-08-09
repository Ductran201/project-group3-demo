package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.impl.ActorDaoImpl;
import ra.model.dto.request.ActorRequest;
import ra.model.entity.Actor;
import ra.service.IActorService;
import ra.service.UploadService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActorServiceImpl implements IActorService {
    @Autowired
    private ActorDaoImpl actorDao;

    @Autowired
    private UploadService uploadService;

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public Actor findById(Long id) {
        return actorDao.findById(id);
    }

    @Override
    public void save(ActorRequest actorRequest) {
        Actor actor = Actor.builder()
                .id(actorRequest.getId())
                .name(actorRequest.getName())
                .build();
        if (actorRequest.getId() == null) {
            actor.setImage(uploadService.uploadFileToServer(actorRequest.getMultipartFile()));
        } else {
            if (actorRequest.getMultipartFile() != null && actorRequest.getMultipartFile().getSize() > 0) {
                actor.setImage(uploadService.uploadFileToServer(actorRequest.getMultipartFile()));
            } else {
                actor.setImage(actorDao.getImageById(actorRequest.getId()));
            }
        }
        actorDao.save(actor);
    }

    @Override
    public void delete(Long id) {
        actorDao.delete(id);
    }


}
