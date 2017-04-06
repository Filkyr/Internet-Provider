package by.bsuir.dao;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> selectCategories(int userId) throws DAOException;
    void insertSubscription(int userId, int categoryId) throws DAOException;
    void deleteSubscription(int userId, int categoryId) throws DAOException;
    boolean getSubscription(int userId, int categoryId) throws DAOException;
}
