package by.bsuir.service;

import by.bsuir.entity.bean.Tariff;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;

import java.util.List;

public interface TariffService {
    int getTariffsAmount(int categoryId) throws ServiceException;
    List<Tariff> getTariffs(int userId, int categoryId, int start, int amount) throws ServiceException;
    List<Tariff> getUserTariffs(int userId) throws ServiceException;
    Tariff getTariff(int userId, String tariffId) throws ServiceDataException, ServiceException;
    int addTariff(String name, String cost, String description, List<String> features,
                  String categoryId) throws ServiceException, ServiceDataException;
    void insertUsage(int userId, String tariffId) throws ServiceDataException, ServiceException;
    void deleteUsage(int userId, String tariffId) throws ServiceDataException, ServiceException;
    void updateSum(String tariffId, String sum) throws ServiceDataException, ServiceException;
    void updateDescription(String tariffId, String description) throws ServiceDataException, ServiceException;
    void updateFeature(String featureId, String feature) throws ServiceDataException, ServiceException;
}
