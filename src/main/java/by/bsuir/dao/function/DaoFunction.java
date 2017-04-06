package by.bsuir.dao.function;

import java.sql.SQLException;

@FunctionalInterface
public interface DaoFunction<T, R> {

    R apply(T t) throws SQLException;
}
