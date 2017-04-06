package by.bsuir.dao.impl;

import by.bsuir.dao.CategoryDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.dao.transaction.TransactionManager;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import by.bsuir.entity.bean.Category;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class CategoryDaoImplTest {
    /*static {
        DAOFactory.getInstance().inject("/bean/dao.xml");
    }
    private static CategoryDao dao = DAOFactory.getInstance().getCategoryDao();
    private static TransactionManager txManager = TransactionManagerImpl.getInstance();

    @BeforeClass
    public static void poolInit(){
        DAOFactory.getInstance().getSourceInit().init();
    }

    @Ignore
    @Test
    public void selectCategoriesTest(){
        try {
            List<Category> list = txManager.doInTransaction(() -> dao.selectCategories(2));
            list.forEach(System.out :: println);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void selectSubscriptionTest(){
        try {
            boolean sub = txManager.doInTransaction(() -> dao.getSubscription(2, 2));
            System.out.println(sub);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void insertSubscriptionTest(){
        try {
            txManager.doInTransaction(() -> {dao.insertSubscription(2, 3); return Optional.empty();});
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void deleteSubscriptionTest(){
        try {
            txManager.doInTransaction(() -> {dao.deleteSubscription(2, 3); return Optional.empty();});
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void poolDestroy(){
        DAOFactory.getInstance().getSourceInit().destroy();
    }*/
}