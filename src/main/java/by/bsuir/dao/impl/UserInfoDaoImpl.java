package by.bsuir.dao.impl;

import by.bsuir.dao.UserInfoDao;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.entity.bean.UserInfo;
import org.springframework.stereotype.Repository;

@Repository("userInfo")
public class UserInfoDaoImpl extends BaseMysqlDao implements UserInfoDao {
    private static final String SELECT_INITIALS = "SELECT CONCAT_WS(' ', `surname`, `name`) FROM users_info WHERE user_id = ?;";
    private static final String INSERT_USER_INFO = "INSERT INTO users_info(user_id, surname, name, last_name, " +
            "mobile_phone) VALUES (?, ?, ?, ?, ?)";

    @Override
    public String selectUserInitials(int userId) throws DAOException {
        return this.performSingleSelect(SELECT_INITIALS, stmt -> stmt.setInt(1, userId),
                rs -> rs.next() ? rs.getString(1) : null);
    }

    @Override
    public void insert(UserInfo userInfo) throws DAOException {
        this.performUpdatable(INSERT_USER_INFO, stmt -> {
                    stmt.setInt(1, userInfo.getUserId());
                    stmt.setString(2, userInfo.getSurname());
                    stmt.setString(3, userInfo.getName());
                    stmt.setString(4, userInfo.getLastName());
                    stmt.setString(5, userInfo.getMobilePhone());});
    }
}
