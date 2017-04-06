package by.bsuir.service.impl;

import by.bsuir.dao.CategoryDao;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.entity.bean.Category;
import by.bsuir.service.CategoryService;
import by.bsuir.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("category")
public class CategoryServiceImpl extends BaseService implements CategoryService {
    private CategoryDao categoryDao = DAOFactory.getInstance().getCategoryDao();

    @Override
    public List<Category> getCategories(int userId) throws ServiceException {
        return this.service(() -> categoryDao.selectCategories(userId));
    }

    @Override
    public boolean getSubscription(int userId, int categoryId) throws ServiceException {
        return this.service(() -> categoryDao.getSubscription(userId, categoryId));
    }

    @Override
    public void changeSubscription(int userId, int categoryId, boolean subscription) throws ServiceException {
        if(subscription){
            this.service(() -> {categoryDao.insertSubscription(userId, categoryId); return Optional.empty();});
        } else {
            this.service(() -> {categoryDao.deleteSubscription(userId, categoryId); return Optional.empty();});
        }
    }
}
