package by.bsuir.dao.impl;

import by.bsuir.dao.CategoryDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.Category;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("category")
public class CategoryDaoImpl extends BaseMysqlDao implements CategoryDao {
    private static final String SELECT_CATEGORIES = "SELECT id, name, coalesce(`table`.`use`, 0) " +
            "FROM (SELECT user_id, category_id, 1 AS `use` FROM subscriptions WHERE user_id = ?) AS `table` " +
            "RIGHT OUTER JOIN categories AS `c` ON `table`.category_id = `c`.id;";
    private static final String SELECT_SUB = "SELECT * FROM subscriptions WHERE user_id = ? AND category_id = ?;";
    private static final String INSERT_SUB = "INSERT INTO subscriptions(user_id, category_id) VALUES(?, ?);";
    private static final String DELETE_SUB = "DELETE FROM subscriptions WHERE user_id = ? AND category_id = ?;";

    @Override
    public List<Category> selectCategories(int userId) throws DAOException {
        return this.performSelect(SELECT_CATEGORIES, stmt -> stmt.setInt(1, userId), this :: build);
    }

    @Override
    public void insertSubscription(int userId, int categoryId) throws DAOException {
        this.performUpdatable(INSERT_SUB, stmt -> {stmt.setInt(1, userId); stmt.setInt(2, categoryId);});
    }

    @Override
    public void deleteSubscription(int userId, int categoryId) throws DAOException {
        this.performUpdatable(DELETE_SUB, stmt -> {stmt.setInt(1, userId); stmt.setInt(2, categoryId);});
    }

    @Override
    public boolean getSubscription(int userId, int categoryId) throws DAOException {
        return this.performSingleSelect(SELECT_SUB, stmt -> {
            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
        }, ResultSet :: next);
    }

    private Category build(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt(1));
        category.setName(rs.getString(2));
        category.setSubscribe(rs.getInt(3) != 0);
        return category;
    }
}
