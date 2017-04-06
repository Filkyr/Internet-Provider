package by.bsuir.service.impl;

import by.bsuir.dao.TariffDao;
import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.entity.bean.Category;
import by.bsuir.entity.bean.Feature;
import by.bsuir.entity.bean.Tariff;
import by.bsuir.service.TariffService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
import by.bsuir.service.util.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("tariff")
public class TariffServiceImpl extends BaseService implements TariffService {
    private final TariffDao tariffDao = DAOFactory.getInstance().getTariffDao();

    @Override
    public int getTariffsAmount(int categoryId) throws ServiceException {
        return this.service(() -> tariffDao.selectAmount(categoryId));
    }

    @Override
    public List<Tariff> getTariffs(int userId, int categoryId, int start, int amount) throws ServiceException {
        return this.service(() -> tariffDao.selectTariffs(userId, categoryId, start, amount));
    }

    @Override
    public List<Tariff> getUserTariffs(int userId) throws ServiceException {
        return this.service(() -> tariffDao.selectUserTariffs(userId));
    }

    @Override
    public Tariff getTariff(int userId, String tariffId) throws ServiceDataException, ServiceException {
        int id = Validator.validateAndGetId(tariffId);
        return this.service(() -> tariffDao.selectTariff(userId, id));
    }

    @Override
    public int addTariff(String name, String cost, String description, List<String> features, String categoryId) throws ServiceException, ServiceDataException {
        int id = this.makeValidation(name, cost, description, features, categoryId);
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setMonthlyCost(Double.parseDouble(cost));
        tariff.setDescription(description);
        tariff.setCategory(new Category(id));
        tariff.setFeatures(features.stream().map(Feature :: new).collect(Collectors.toList()));

        int generatedId = this.service(() -> tariffDao.insert(tariff));

        List<String> receivers = this.service(() -> DAOFactory.getInstance().getUserDao().selectSubscribersEmail(id));
        ServiceFactory.getInstance().getMailService().sendEmail(receivers,
                "Добавлен новый тариф", "Здравствуйте, мы рады сообщить вам, что на нашем сервисе появился новый тарифный " +
                        "план - " + name);

        return generatedId;
    }

    @Override
    public void insertUsage(int userId, String tariffId) throws ServiceDataException, ServiceException {
        int id = Validator.validateAndGetId(tariffId);
        this.service(() -> {tariffDao.insertUsage(userId, id); return Optional.empty();});
    }

    @Override
    public void deleteUsage(int userId, String tariffId) throws ServiceDataException, ServiceException {
        int id = Validator.validateAndGetId(tariffId);
        this.service(() -> {tariffDao.deleteUsage(userId, id); return Optional.empty();});
    }

    @Override
    public void updateSum(String tariffId, String sum) throws ServiceDataException, ServiceException {
        int id = Validator.validateAndGetId(tariffId);
        Validator.validateSum(sum);

        this.service(() -> {tariffDao.updateSum(id, Double.parseDouble(sum)); return Optional.empty();});

        List<String> receivers = this.service(() -> DAOFactory.getInstance().getUserDao().selectOwnerEmail(id));
        ServiceFactory.getInstance().getMailService().sendEmail(receivers,
                "Обновлена сумма тарифного плана", "Здравствуйте, мы хотим сообщить вам, что изменилась цена на тариф, которым вы пользуетесь.");
    }

    @Override
    public void updateDescription(String tariffId, String description) throws ServiceDataException, ServiceException {
        int id = Validator.validateAndGetId(tariffId);
        Validator.validateDescription(description);

        this.service(() -> {tariffDao.updateDescription(id, description); return Optional.empty();});

        List<String> receivers = this.service(() -> DAOFactory.getInstance().getUserDao().selectOwnerEmail(id));
        ServiceFactory.getInstance().getMailService().sendEmail(receivers,
                "Обновлено описание тарифного плана", "Здравствуйте, мы хотим сообщить вам, что изменилась описание тарифа, который вы используете.");
    }

    @Override
    public void updateFeature(String featureId, String feature) throws ServiceDataException, ServiceException {
        int id = Validator.validateAndGetId(featureId);
        Validator.validateFeature(feature);

        this.service(() -> {tariffDao.updateFeature(id, feature); return Optional.empty();});

        List<String> receivers = this.service(() -> DAOFactory.getInstance().getUserDao().selectOwnerEmail(id));
        ServiceFactory.getInstance().getMailService().sendEmail(receivers,
                "Обновлено преимущество тарифного плана", "Здравствуйте, мы хотим сообщить вам, что изменилось преимущество тарифа, который вы используете.");
    }

    private int makeValidation(String name, String cost, String description, List<String> features, String categoryId) throws ServiceDataException {
        Validator.validateTariffName(name);
        Validator.validateSum(cost);
        Validator.validateDescription(description);
        for(String feature : features){
            Validator.validateFeature(feature);
        }
        return Validator.validateAndGetId(categoryId);
    }
}
