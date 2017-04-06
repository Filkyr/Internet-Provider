package by.bsuir.dao.impl;

import by.bsuir.dao.TariffDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.dao.transaction.TransactionManager;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import by.bsuir.entity.bean.Category;
import by.bsuir.entity.bean.Feature;
import by.bsuir.entity.bean.Tariff;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TariffDaoImplTest {
    /*static {
        DAOFactory.getInstance().inject("/bean/dao.xml");
    }
    private static TariffDao dao = DAOFactory.getInstance().getTariffDao();
    private static TransactionManager txManager = TransactionManagerImpl.getInstance();

    @BeforeClass
    public static void poolInit(){
        DAOFactory.getInstance().getSourceInit().init();
    }

    @Ignore
    @Test
    public void selectTariffsTest(){
        try {
            List<Tariff> list = txManager.doInTransaction(() -> dao.selectTariffs(2, 1, 0, 10));
            list.forEach(System.out :: println);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void selectTariffTest(){
        try {
            Tariff tariff = txManager.doInTransaction(() -> dao.selectTariff(2, 1));
            System.out.println(tariff);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void insertTariffTest(){
        Tariff tariff = new Tariff();
        tariff.setName("Тариф");
        tariff.setMonthlyCost(14.86);
        tariff.setDescription("Описание тарифа");
        Category category = new Category();
        category.setId(2);
        tariff.setCategory(category);
        Feature feature = new Feature();
        feature.setFeature("Особенность тарифа");
        tariff.setFeatures(new ArrayList<>(Arrays.asList(feature)));
        try {
            int id = txManager.doInTransaction(() -> dao.insert(tariff));
            System.out.println(id);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void deleteUsageTest(){
        try {
            txManager.doInTransaction(() -> {dao.deleteUsage(2, 5); return Optional.empty();});
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void updateSumTest(){
        try {
            txManager.doInTransaction(() -> {dao.updateSum(1, 15.33); return Optional.empty();});
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void updateDescriptionTest(){
        try {
            txManager.doInTransaction(() -> {dao.updateDescription(1, "Описание тарифа"); return Optional.empty();});
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateFeatureTest(){
        try {
            txManager.doInTransaction(() -> {dao.updateFeature(1, "Не баг, а фича!!"); return Optional.empty();});
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void poolDestroy(){
        DAOFactory.getInstance().getSourceInit().destroy();
    }*/
}