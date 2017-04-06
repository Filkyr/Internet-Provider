package by.bsuir.service;

import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.entity.bean.User;
import by.bsuir.service.exception.ServiceException;

public interface UserService {
    User getByEmail(String email) throws ServiceException, ServiceDataException;

    boolean isEmailFree(String email) throws ServiceException, ServiceDataException;

    String getUserEmail(int userId) throws ServiceException;

    int getBannedCount() throws ServiceException;

    int getAllUsersCount() throws ServiceException;

    boolean getUserStatus(String userId) throws ServiceException, ServiceDataException;

    int createUser(String email, byte[] password) throws ServiceException, ServiceDataException;

    void changeUserStatus(boolean isBanned, String userId) throws ServiceException, ServiceDataException;

    void deleteUser(String userId) throws ServiceException, ServiceDataException;
}
