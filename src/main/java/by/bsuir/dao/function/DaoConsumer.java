package by.bsuir.dao.function;

import java.sql.SQLException;

@FunctionalInterface
public interface DaoConsumer<T> {

    void accept(T t) throws SQLException;
}
