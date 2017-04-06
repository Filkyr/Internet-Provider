package by.bsuir.dao.impl;

import by.bsuir.dao.UserDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.dao.transaction.TransactionManager;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import by.bsuir.entity.bean.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class UserDaoImplTest {
    /*static {
        DAOFactory.getInstance().inject("/bean/dao.xml");
    }
    private static UserDao dao = DAOFactory.getInstance().getUserDao();
    private static TransactionManager txManager = TransactionManagerImpl.getInstance();

    @BeforeClass
    public static void poolInit(){
        DAOFactory.getInstance().getSourceInit().init();
    }

    @Ignore
    @Test
    public void updateFeatureTest(){
        try {
            User user = txManager.doInTransaction(() -> dao.selectByEmail("alexkonsky@mail.ru"));
            System.out.println(user);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void isEmailFreeTest(){
        try {
            boolean b = txManager.doInTransaction(() -> dao.isEmailFree("alexkonsky@mail.ru"));
            System.out.println(b);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void selectSubsEmailTest(){
        try {
            List<String> list = txManager.doInTransaction(() -> dao.selectSubscribersEmail(2));
            list.forEach(System.out :: println);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void poolDestroy(){
        DAOFactory.getInstance().getSourceInit().destroy();
    }*/
}