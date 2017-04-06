package by.bsuir.dao.impl;

import by.bsuir.dao.UserDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("user")
public class UserDaoImpl extends BaseMysqlDao implements UserDao {
    private static final String SELECT_BY_EMAIL = "SELECT id, email, password, is_admin FROM users WHERE email = ?;";
    private static final String IS_EMAIL_FREE = "SELECT id FROM users WHERE email = ?";
    private static final String SELECT_SUBS = "SELECT email FROM subscriptions AS s INNER JOIN users AS u ON s.user_id = u.id WHERE category_id = ?;";
    private static final String SELECT_OWNS = "SELECT email FROM services_list AS sl INNER JOIN users AS u ON sl.user_id = u.id WHERE product_id= ?;";
    private static final String INSERT_USER = "INSERT INTO users (email, password) VALUES (?, ?);";

    @Override
    public User selectByEmail(String email) throws DAOException {
        return this.performSingleSelect(
                SELECT_BY_EMAIL,
                stmt -> stmt.setString(1, email),
                rs -> rs.next() ? this.setUserData(rs) : null
        );
    }

    @Override
    public boolean isEmailFree(String email) throws DAOException {
        return this.performSingleSelect(IS_EMAIL_FREE, stmt -> stmt.setString(1, email), rs -> !rs.next());
    }

    @Override
    public List<String> selectSubscribersEmail(int categoryId) throws DAOException {
        return this.performSelect(SELECT_SUBS, stmt -> stmt.setInt(1, categoryId), rs -> rs.getString(1));
    }

    @Override
    public List<String> selectOwnerEmail(int productId) throws DAOException {
        return this.performSelect(SELECT_OWNS, stmt -> stmt.setInt(1, productId), rs -> rs.getString(1));
    }

    @Override
    public int insert(User user) throws DAOException {
        return this.performInsert(INSERT_USER, stmt -> {
                    stmt.setString(1, user.getEmail());
                    stmt.setString(2, Base64.encode(user.getPassword()));
                }, rs -> {rs.next(); return rs.getInt(1);});
    }

    private User setUserData(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setEmail(resultSet.getString(2));
        user.setPassword(Base64.decode(resultSet.getString(3)));
        user.setAdmin(resultSet.getBoolean(4));
        return user;
    }
}
