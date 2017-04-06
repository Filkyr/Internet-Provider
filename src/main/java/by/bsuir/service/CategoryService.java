package by.bsuir.service;

import by.bsuir.entity.bean.Category;
import by.bsuir.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories(int userId) throws ServiceException;
    boolean getSubscription(int userId, int categoryId) throws ServiceException;
    void changeSubscription(int userId, int categoryId, boolean subscription) throws ServiceException;
}
