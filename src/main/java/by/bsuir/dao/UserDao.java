package by.bsuir.dao;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.User;

import java.util.List;

public interface UserDao {

    User selectByEmail(String email) throws DAOException;
    boolean isEmailFree(String email) throws DAOException;
    List<String> selectSubscribersEmail(int categoryId) throws DAOException;
    List<String> selectOwnerEmail(int productId) throws DAOException;
    int insert(User user) throws DAOException;
}
