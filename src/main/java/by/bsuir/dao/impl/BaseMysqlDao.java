package by.bsuir.dao.impl;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.exception.NonTransactionInvocationException;
import by.bsuir.dao.function.DaoConsumer;
import by.bsuir.dao.function.DaoFunction;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j
public abstract class BaseMysqlDao {
    @Autowired
    private DataSource dataSource = (DataSource) TransactionManagerImpl.getInstance();

    public final void performUpdatable(String query, DaoConsumer<PreparedStatement> setter) throws DAOException {
        try {
            Connection connection = dataSource.getConnection();
            this.checkConnection(connection);
            @Cleanup PreparedStatement statement = connection.prepareStatement(query);

            setter.accept(statement);

            statement.executeUpdate();
        } catch(SQLException e){
            log.error("Can't execute SQL - " + query, e);
            throw new DAOException(e);
        }
    }

    public final <K> K performInsert(String query, DaoConsumer<PreparedStatement> setter,
                                     DaoFunction<ResultSet, K> builder) throws DAOException {
        try {
            Connection connection = dataSource.getConnection();
            this.checkConnection(connection);
            @Cleanup PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            setter.accept(statement);

            statement.executeUpdate();

            @Cleanup ResultSet resultSet = statement.getGeneratedKeys();

            return builder.apply(resultSet);
        } catch(SQLException e){
            log.error("Can't execute SQL - " + query, e);
            throw new DAOException(e);
        }
    }

    public final <O> List<O> performSelect(String query, DaoConsumer<PreparedStatement> setter,
                                           DaoFunction<ResultSet, O> builder) throws DAOException {
        return this.performSingleSelect(
                query,
                setter,
                resultSet -> {
                    List<O> list = new ArrayList<>();
                    while(resultSet.next()){
                        list.add(builder.apply(resultSet));
                    }
                    return list;
                }
        );
    }

    public final <O> O performSingleSelect(String query, DaoConsumer<PreparedStatement> setter,
                                           DaoFunction<ResultSet, O> builder) throws DAOException {
        try {
            Connection connection = dataSource.getConnection();
            this.checkConnection(connection);
            @Cleanup PreparedStatement statement = connection.prepareStatement(query);

            setter.accept(statement);

            @Cleanup ResultSet resultSet = statement.executeQuery();

            return builder.apply(resultSet);
        } catch(SQLException e){
            log.error("Can't execute SQL - " + query, e);
            throw new DAOException(e);
        }
    }

    public final <O> List<O> performSelect(String query, DaoFunction<ResultSet, O> builder) throws DAOException {
        return this.performSingleSelect(
                query,
                resultSet -> {
                    List<O> list = new ArrayList<>();
                    while(resultSet.next()){
                        list.add(builder.apply(resultSet));
                    }
                    return list;
                }
        );
    }

    public final <O> O performSingleSelect(String query, DaoFunction<ResultSet, O> builder) throws DAOException {
        try {
            Connection connection = dataSource.getConnection();
            this.checkConnection(connection);
            @Cleanup Statement statement = connection.createStatement();

            @Cleanup ResultSet resultSet = statement.executeQuery(query);

            return builder.apply(resultSet);
        } catch(SQLException e){
            log.error("Can't execute SQL - " + query, e);
            throw new DAOException(e);
        }
    }

    public void checkConnection(Connection connection){
        if(connection == null){
            throw new NonTransactionInvocationException("Can't invoke DAO method outside of 'doInTransaction'");
        }
    }
}
