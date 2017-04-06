package by.bsuir.dao.impl;

import by.bsuir.dao.TariffDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.Category;
import by.bsuir.entity.bean.Feature;
import by.bsuir.entity.bean.Tariff;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j
@Repository("tariff")
public class TariffDaoImpl extends BaseMysqlDao implements TariffDao {
    private static final String SELECT_TARIFFS = "SELECT p.id, p.name, p.monthly_cost, p.description, c.name, coalesce(`usage`.`use`, 0) " +
            "FROM (SELECT user_id, product_id, 1 AS `use` FROM services_list WHERE user_id = ?) AS `usage` " +
            "RIGHT OUTER JOIN products AS p ON `usage`.product_id = p.id " +
            "INNER JOIN categories AS c ON p.category_id = c.id WHERE p.category_id = ? LIMIT ?, ?;";
    private static final String SELECT_USER_TARIFFS = "SELECT p.id, p.name, p.monthly_cost, p.description, c.name, 1 " +
            "FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id " +
            "INNER JOIN services_list AS sl ON p.id = sl.product_id WHERE sl.user_id = ?;";
    private static final String SELECT_TARIFF = "SELECT p.id, p.name, p.monthly_cost, p.description, c.name, coalesce(`usage`.`use`, 0) " +
            "FROM (SELECT user_id, product_id, 1 AS `use` FROM services_list WHERE user_id = ?) AS `usage` " +
            "RIGHT OUTER JOIN products AS p ON `usage`.product_id = p.id " +
            "INNER JOIN categories AS c ON p.category_id = c.id WHERE p.id = ?;";
    private static final String SELECT_FEATURES = "SELECT id, product_id, feature FROM features WHERE product_id = ?;";
    private static final String INSERT_TARIFF = "INSERT INTO products (name, monthly_cost, description, category_id) " +
            "VALUES (?, ?, ?, ?);";
    private static final String INSERT_FEATURE = "INSERT INTO features (product_id, feature) VALUES (?, ?);";
    private static final String INSERT_USAGE = "INSERT INTO services_list (user_id, product_id) VALUES(?, ?);";
    private static final String DELETE_USAGE = "DELETE FROM services_list WHERE user_id = ? AND product_id = ?;";
    private static final String UPDATE_SUM = "UPDATE products SET monthly_cost = ? WHERE id = ?;";
    private static final String UPDATE_DESCRIPTION = "UPDATE products SET description = ? WHERE id = ?;";
    private static final String UPDATE_FEATURE = "UPDATE features SET feature = ? WHERE id = ?;";
    private static final String SELECT_AMOUNT = "SELECT COUNT(*) FROM products WHERE category_id = ?;";

    @Override
    public int selectAmount(int categoryId) throws DAOException {
        return this.performSingleSelect(SELECT_AMOUNT, stmt -> stmt.setInt(1, categoryId), rs -> rs.next() ? rs.getInt(1) : 0);
    }

    @Override
    public List<Tariff> selectTariffs(int userId, int categoryId, int start, int amount) throws DAOException {
        return this.performSelect(SELECT_TARIFFS, stmt -> {
            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setInt(3, start);
            stmt.setInt(4, amount);
        }, this :: build);
    }

    @Override
    public List<Tariff> selectUserTariffs(int userId) throws DAOException {
        return this.performSelect(SELECT_USER_TARIFFS, stmt -> stmt.setInt(1, userId), this :: build);
    }

    @Override
    public Tariff selectTariff(int userId, int tariffId) throws DAOException {
        Tariff tariff = this.performSingleSelect(SELECT_TARIFF, stmt -> {
            stmt.setInt(1, userId);
            stmt.setInt(2, tariffId);
        }, rs -> rs.next() ? this.build(rs) : null);

        tariff.setFeatures(this.performSelect(SELECT_FEATURES, stmt -> stmt.setInt(1, tariffId), this :: buildFeature));
        return tariff;
    }

    @Override
    public int insert(Tariff tariff) throws DAOException {
        int id = this.performInsert(INSERT_TARIFF, stmt -> {
            stmt.setString(1, tariff.getName());
            stmt.setDouble(2, tariff.getMonthlyCost());
            stmt.setString(3, tariff.getDescription());
            stmt.setInt(4, tariff.getCategory().getId());
        }, rs -> {rs.next(); return rs.getInt(1);});

        for(Feature feature : tariff.getFeatures()){
            this.performUpdatable(INSERT_FEATURE, stmt -> {
                stmt.setInt(1, id);
                stmt.setString(2, feature.getFeature());
            });
        }

        return id;
    }

    @Override
    public void insertUsage(int userId, int tariffId) throws DAOException {
        this.performUpdatable(INSERT_USAGE, stmt -> {stmt.setInt(1, userId); stmt.setInt(2, tariffId);});
    }

    @Override
    public void deleteUsage(int userId, int tariffId) throws DAOException {
        this.performUpdatable(DELETE_USAGE, stmt -> {stmt.setInt(1, userId); stmt.setInt(2, tariffId);});
    }

    @Override
    public void updateSum(int tariffId, double sum) throws DAOException {
        this.performUpdatable(UPDATE_SUM, stmt -> {stmt.setDouble(1, sum); stmt.setInt(2, tariffId);});
    }

    @Override
    public void updateDescription(int tariffId, String description) throws DAOException {
        this.performUpdatable(UPDATE_DESCRIPTION, stmt -> {stmt.setString(1, description); stmt.setInt(2, tariffId);});
    }

    @Override
    public void updateFeature(int featureId, String feature) throws DAOException {
        this.performUpdatable(UPDATE_FEATURE, stmt -> {stmt.setString(1, feature); stmt.setInt(2, featureId);});
    }

    private Tariff build(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(rs.getInt(1));
        tariff.setName(rs.getString(2));
        tariff.setMonthlyCost(rs.getDouble(3));
        tariff.setDescription(rs.getString(4));
        Category category = new Category();
        category.setName(rs.getString(5));
        tariff.setCategory(category);
        tariff.setUsed(rs.getBoolean(6));
        return tariff;
    }

    private Feature buildFeature(ResultSet rs) throws SQLException {
        Feature feature = new Feature();
        feature.setId(rs.getInt(1));
        feature.setProductId(rs.getInt(2));
        feature.setFeature(rs.getString(3));
        return feature;
    }
}
