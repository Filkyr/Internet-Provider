package by.bsuir.dao;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.Tariff;

import java.util.List;

public interface TariffDao {

    int selectAmount(int categoryId) throws DAOException;
    List<Tariff> selectTariffs(int userId, int categoryId, int start, int amount) throws DAOException;
    List<Tariff> selectUserTariffs(int userId) throws DAOException;
    Tariff selectTariff(int userId, int tariffId) throws DAOException;
    int insert(Tariff tariff) throws DAOException;
    void insertUsage(int userId, int tariffId) throws DAOException;
    void deleteUsage(int userId, int tariffId) throws DAOException;

    void updateSum(int tariffId, double sum) throws DAOException;
    void updateDescription(int tariffId, String description) throws DAOException;
    void updateFeature(int featureId, String feature) throws DAOException;
}
