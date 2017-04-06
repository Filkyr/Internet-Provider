package by.bsuir.dao;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.UserInfo;

public interface UserInfoDao {

    String selectUserInitials(int userId) throws DAOException;
    void insert(UserInfo userInfo) throws DAOException;
}
