package by.bsuir.service;

import by.bsuir.entity.bean.UserInfo;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;

import java.util.List;

public interface UserInfoService {
    String getUserInitials(int userId) throws ServiceException;

    List<UserInfo> getLimitedUser(int start, int amount, String language) throws ServiceException;

    List<UserInfo> getLimitedAndBannedUser(int start, int amount, boolean banned, String language) throws ServiceException;

    boolean isUserInfoFull(int userId) throws ServiceException;

    List<String> searchInUserInitials(String search, String language) throws ServiceException;

    void addUserInfo(UserInfo user) throws ServiceException, ServiceDataException;
}
