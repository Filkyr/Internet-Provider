package by.bsuir.controller.filter;

import by.bsuir.command.Command;
import by.bsuir.command.CommandManager;
import by.bsuir.controller.exception.CommandInitializationException;
import by.bsuir.util.command.Cmd;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log4j
public class CommandFilter implements Filter {
    private static final Map<String, String> commands = new HashMap<>();
    private static final String CMD = "cmd";
    private static final String WELCOME_PAGE = "welcome-page";
    private static final String COMMANDS_PACKAGE = "by.bsuir.command.impl";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        CommandManager manager = CommandManager.getInstance();
        Reflections reflections = new Reflections(COMMANDS_PACKAGE);
        Set<Class<?>> col = reflections.getTypesAnnotatedWith(Cmd.class);

        try {
            for(Class<?> clazz : col){
                Cmd annotation = clazz.getAnnotation(Cmd.class);
                commands.put(annotation.name(), annotation.method().toString());
                manager.addCommand(annotation.name(), (Command) clazz.newInstance());
            }
        } catch(IllegalAccessException | InstantiationException e){
            log.error(e);
            throw new CommandInitializationException("Can't initialize command", e);
        }

        log.info("CommandFilter set up all commands for himself and for CommandManager");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(CMD);
        if(command != null && request.getMethod().equals(commands.get(command))){
            filterChain.doFilter(request, response);
        } else {
            CommandManager.getInstance().getCommand(WELCOME_PAGE).execute(request, response);
        }
    }

    @Override
    public void destroy() {
        commands.clear();
    }
}