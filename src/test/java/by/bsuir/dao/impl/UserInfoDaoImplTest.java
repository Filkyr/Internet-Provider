package by.bsuir.dao.impl;

import by.bsuir.dao.UserInfoDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.dao.transaction.TransactionManager;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserInfoDaoImplTest {
    /*static {
        DAOFactory.getInstance().inject("/bean/dao.xml");
    }
    private static UserInfoDao dao = DAOFactory.getInstance().getUserInfoDao();
    private static TransactionManager txManager = TransactionManagerImpl.getInstance();

    @BeforeClass
    public static void poolInit(){
        DAOFactory.getInstance().getSourceInit().init();
    }

    @Test
    public void selectSubsEmailTest(){
        try {
            String initials = txManager.doInTransaction(() -> dao.selectUserInitials(2));
            System.out.println(initials);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void poolDestroy(){
        DAOFactory.getInstance().getSourceInit().destroy();
    }*/
}