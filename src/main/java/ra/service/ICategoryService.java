package ra.service;

import ra.model.entity.Category;

public interface ICategoryService extends IGenericServiceCRUD<Category,Integer> {
    void save(Category category);
}
