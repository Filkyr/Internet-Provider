package by.bsuir.service.impl;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.dao.UserDao;
import by.bsuir.dao.transaction.TransactionManager;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import by.bsuir.entity.bean.User;
import by.bsuir.service.UserService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.util.PasswordManager;
import by.bsuir.service.util.Validator;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Log4j
@Service("user")
public class UserServiceImpl extends BaseService implements UserService {
    private static TransactionManager txManager = TransactionManagerImpl.getInstance();
    private final UserDao userDao = DAOFactory.getInstance().getUserDao();

    @Override
    public User getByEmail(String email) throws ServiceException, ServiceDataException {
        Validator.validateEmail(email);

        return this.service(() -> userDao.selectByEmail(email));
    }

    @Override
    public boolean isEmailFree(String email) throws ServiceException, ServiceDataException {
        Validator.validateEmail(email);

        return this.service(() -> userDao.isEmailFree(email));
    }

    @Override
    public String getUserEmail(int userId) throws ServiceException {
//        return this.service("Can't get user email", () -> userDao.selectEmail(userId));
        return null;
    }

    @Override
    public int getBannedCount() throws ServiceException {
//        return this.service("Can't get amount of banned users", userDao :: selectBannedCount);
        return 0;
    }

    @Override
    public int getAllUsersCount() throws ServiceException {
//        return this.service("Can't get amount of users", userDao :: selectUserCount);
        return 0;
    }

    @Override
    public boolean getUserStatus(String userId) throws ServiceException, ServiceDataException {
        /*int id = Validator.validateAndGetId(userId);

        return this.service("Can't get user status", () -> userDao.isUserBanned(id));*/
        return false;
    }

    @Override
    public int createUser(String email, byte[] password) throws ServiceException, ServiceDataException {
        Validator.validateEmail(email);
        Validator.validatePassword(password);

        User user = new User();
        user.setEmail(email);
        byte[] encryptPass = PasswordManager.getInstance().encryptPassword(password);
        user.setPassword(encryptPass);

        try {
            return txManager.doInTransaction(() -> userDao.insert(user));
        } catch(DAOException e){
            log.error(e);
            throw new ServiceException(e);
        } finally {
            PasswordManager.dispose(encryptPass);
        }
    }

    @Override
    public void changeUserStatus(boolean isBanned, String userId) throws ServiceException, ServiceDataException {
        /*int id = Validator.validateAndGetId(userId);

        this.service("Can't change user status",
                () -> {
                    userDao.updateUserStatus(isBanned, id);
                    return Optional.empty();
                });*/
    }

    @Override
    public void deleteUser(String userId) throws ServiceException, ServiceDataException {
        /*int id = Validator.validateAndGetId(userId);
        AccountDAO accountDao = DAOFactory.getInstance().getCategoryDao();
        UserInfoDAO userInfoDao = DAOFactory.getInstance().getProductDao();

        Account account = new Account();
        User user = new User();
        UserInfo userInfo = new UserInfo();
        user.setId(id);
        account.setUser(user);
        userInfo.setUserId(id);

        this.service("Can't delete user",
                () -> {
                    userInfoDao.delete(userInfo);
                    accountDao.delete(account);
                    userDao.delete(user);
                    return Optional.empty();
                });*/
    }
}
