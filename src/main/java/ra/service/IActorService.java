package ra.service;

import ra.model.dto.request.ActorRequest;
import ra.model.entity.Actor;

public interface IActorService extends IGenericServiceCRUD<Actor,Long> {
    void save(ActorRequest actorRequest);

}
