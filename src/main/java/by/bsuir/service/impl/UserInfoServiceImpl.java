package by.bsuir.service.impl;

import by.bsuir.dao.UserInfoDao;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.entity.bean.UserInfo;
import by.bsuir.service.UserInfoService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.util.Validator;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j
@Service("userInfo")
public class UserInfoServiceImpl extends BaseService implements UserInfoService {
//    private static final int FULL_USER_INFO_NUMBER = 2;
//    private static TransactionManager txManager = TransactionManagerImpl.getInstance();
    private final UserInfoDao userInfoDao = DAOFactory.getInstance().getUserInfoDao();

    @Override
    public String getUserInitials(int userId) throws ServiceException {
        return this.service(() -> userInfoDao.selectUserInitials(userId));
    }

    @Override
    public List<UserInfo> getLimitedUser(int start, int amount, String language) throws ServiceException {
//        return this.service("Can't get limited user information by language",
//                () -> userInfoDao.selectLimited(start, amount, language));
        return null;
    }

    @Override
    public List<UserInfo> getLimitedAndBannedUser(int start, int amount, boolean banned, String language) throws ServiceException {
//        return this.service("Can't get limited user information by language and banned",
//                () -> userInfoDao.selectLimitedAndBanned(start, amount, banned, language));
        return null;
    }

    @Override
    public boolean isUserInfoFull(int userId) throws ServiceException {
//        return this.service("Can't determine is user information full",
//                () -> userInfoDao.selectInfoCount(userId)) == FULL_USER_INFO_NUMBER;
        return false;
    }

    @Override
    public List<String> searchInUserInitials(String search, String language) throws ServiceException {
        //We don't need to validate search string, because it can be whatever you want (even null)
//        return this.service("Can't search user initials by language and search string",
//                () -> userInfoDao.searchInUserInitials(search, language));
        return null;
    }

    @Override
    public void addUserInfo(UserInfo user) throws ServiceException, ServiceDataException {
        this.makeValidation(user);

        this.service(() -> {userInfoDao.insert(user); return Optional.empty();});
    }

    private String changeLanguage(String language){
//        return language.equals(Attribute.ru_RU) ? Attribute.en_EN : Attribute.ru_RU;
        return null;
    }

    private void makeValidation(UserInfo user) throws ServiceDataException {
        Validator.validateInitials(user.getSurname());
        Validator.validateInitials(user.getName());
        Validator.validateLastName(user.getLastName());
        Validator.validateMobilePhone(user.getMobilePhone());
    }
}
