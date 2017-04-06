package by.bsuir.controller.filter;

import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.util.TextUtil;
import by.bsuir.util.command.Cmd;
import by.bsuir.util.command.Role;
import lombok.extern.log4j.Log4j;
import org.reflections.Reflections;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j
public class RoleFilter implements Filter {
    private static final Map<String, List<String>> roles = new HashMap<>();
    private static final String CMD = "cmd";
    private static final String GET = "GET";
    private static final String WELCOME_PAGE = "welcome-page";
    private static final String COMMANDS_PACKAGE = "by.bsuir.command.impl";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Reflections reflections = new Reflections(COMMANDS_PACKAGE);
        Set<Class<?>> col = reflections.getTypesAnnotatedWith(Cmd.class);

        for(Class<?> clazz : col){
            Cmd annotation = clazz.getAnnotation(Cmd.class);
            Role[] roles = annotation.roles();
            if(roles.length == 1 && roles[0] == Role.ALL){
                RoleFilter.roles.put(annotation.name(), new ArrayList<>());
            } else {
                RoleFilter.roles.put(annotation.name(), Stream.of(roles).map(Object :: toString).collect(Collectors.toList()));
            }
        }

        log.info("RoleFilter set up all roles for himself");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(CMD);
        command = (command == null) ? WELCOME_PAGE : command;

        HttpSession session = request.getSession(false);
        String role = (session == null) ? null : (String) session.getAttribute(Attribute.ROLE);

        if(this.isRoleTrue(command, role)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + TextUtil.SLASH + PagePath.WELCOME_PAGE);
        }
    }

    @Override
    public void destroy() {
        roles.clear();
    }

    private boolean isRoleTrue(String command, String role){
        List<String> list = roles.get(command);
        return list.isEmpty() || list.contains(role);
    }
}
