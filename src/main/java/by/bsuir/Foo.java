package by.bsuir;

import by.bsuir.command.Command;
import by.bsuir.util.command.Method;
import by.bsuir.util.command.Role;
import lombok.extern.log4j.Log4j;
import org.reflections.Reflections;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ServiceLoader;
import java.util.Set;

@Log4j
public class Foo {
    private static final String DB_PROPERTIES_PATH = "mysqldb";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    public static void main(String[] args) throws PropertyVetoException, SQLException {
        /*ResourceBundle dbProp = ResourceBundle.getBundle(DB_PROPERTIES_PATH);
        ComboPooledDataSource cp = new ComboPooledDataSource();
        cp.setDriverClass(dbProp.getString(DB_DRIVER));
        cp.setJdbcUrl(dbProp.getString(DB_URL));
        cp.setUser(dbProp.getString(DB_USER));
        cp.setPassword(dbProp.getString(DB_PASSWORD));

        cp.setInitialPoolSize(1);
        cp.setMaxStatements(5);
        cp.setMaxStatementsPerConnection(5);
        cp.setMinPoolSize(1);
        cp.setAcquireIncrement(1);
        cp.setMaxPoolSize(2);
        cp.setMaxIdleTime(30);

        try(Connection conn = cp.getConnection()){

        }

        cp.close();*/

//        Map<String, Object> map = new HashMap<>();
//        map.put("1", "12");
//        int i = (int) map.get("2");
//        String s = (String) map.get("2");
//        System.out.println(s);

        /*Reflections reflections = new Reflections("by.bsuir.command.impl");
        Set<Class<?>> col = reflections.getTypesAnnotatedWith(by.bsuir.util.command.Cmd.class);
        for(Class<?> clazz : col){
            by.bsuir.util.command.Cmd annotation = clazz.getAnnotation(by.bsuir.util.command.Cmd.class);
            System.out.println(annotation.name());
        }*/

//        System.out.println(Role.NULL);

        ServiceLoader<Command> load = ServiceLoader.load(Command.class);
        load.forEach(System.out :: println);
    }
}