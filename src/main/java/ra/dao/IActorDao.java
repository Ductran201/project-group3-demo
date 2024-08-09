package ra.dao;

import ra.model.entity.Actor;

public interface IActorDao extends IGenericDaoCRUD<Actor,Long> {
    public String getImageById(Long id);

}
