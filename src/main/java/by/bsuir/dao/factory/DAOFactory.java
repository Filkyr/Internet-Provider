package by.bsuir.dao.factory;

import by.bsuir.dao.CategoryDao;
import by.bsuir.dao.TariffDao;
import by.bsuir.dao.UserDao;
import by.bsuir.dao.UserInfoDao;
import by.bsuir.dao.impl.MySQLPoolInit;
import by.bsuir.util.injection.Inject;
import by.bsuir.util.injection.Injectable;
import lombok.Getter;

@Getter
public class DAOFactory implements Injectable {
    private static final String DAO_CONFIG = "/bean/dao.xml";
    private static final DAOFactory INSTANCE = new DAOFactory();

    @Inject(beanId = "user")
    private UserDao userDao;

    @Inject(beanId = "userInfo")
    private UserInfoDao userInfoDao;

    @Inject(beanId = "category")
    private CategoryDao categoryDao;

    @Inject(beanId = "tariff")
    private TariffDao tariffDao;

    @Inject(beanId = "pool")
    private MySQLPoolInit sourceInit;

    private DAOFactory(){
        this.inject(DAO_CONFIG);
    }

    public static DAOFactory getInstance(){
        return INSTANCE;
    }
}
