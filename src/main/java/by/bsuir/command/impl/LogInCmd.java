package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.entity.bean.User;
import by.bsuir.service.UserService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
import by.bsuir.service.util.PasswordManager;
import by.bsuir.util.CookieHelper;
import by.bsuir.util.TextUtil;
import by.bsuir.util.command.Cmd;
import by.bsuir.util.command.Method;
import by.bsuir.util.command.Role;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j
@Cmd(name = "log-in", method = Method.POST, roles = {Role.ALL})
public class LogInCmd implements Command {
    private static final int MAX_COOKIE_ALIVE_TIME = 120;
    private static final String REDIRECT = "/Controller";
    private static final String CONTROLLER = "/Controller?cmd=";
    private static final String REGISTER_PAGE = "register-page";
    private static final String FAIL_LOGIN_PAGE = "fail-login-page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Attribute.EMAIL);
        byte[] password = request.getParameter(Attribute.PASSWORD).getBytes(StandardCharsets.UTF_8);

        HttpSession session = request.getSession();
        String context = request.getContextPath();
        try {
            UserService service = ServiceFactory.getInstance().getUserService();
            User user = service.getByEmail(email);

            this.logIn(user, password, session, context, email, response);
        } catch(ServiceException e){
            log.error(e);
            session.setAttribute(Attribute.ROLE, Attribute.GUEST_ROLE);
            response.sendRedirect(TextUtil.concat(context, TextUtil.SLASH, PagePath.PATH_TO_500_PAGE));
        } catch(ServiceDataException e){
            log.warn("While logging in error occurred. Input email - " + email, e);
            this.toFailLoginPage(session, email, response, context);
        }
    }

    private void logIn(User user, byte[] password, HttpSession session, String context, String email,
                       HttpServletResponse response) throws IOException, ServiceException {
        if(user != null){
            this.userExistHandler(user, password, session, context, email, response);
        } else {
            PasswordManager.dispose(password);
            this.toFailLoginPage(session, email, response, context);
        }
    }

    private void userExistHandler(User user, byte[] password, HttpSession session, String context, String email,
                                  HttpServletResponse response) throws IOException, ServiceException {
        if(PasswordManager.getInstance().match(password, user.getPassword())){
            this.defineRole(user, session, context, response);
        } else {
            this.toFailLoginPage(session, email, response, context);
        }
    }

    private void defineRole(User user, HttpSession session, String context,
                            HttpServletResponse response) throws IOException, ServiceException {
        if(user.isAdmin()){
            session.setAttribute(Attribute.ROLE, Attribute.ADMIN_ROLE);
            session.setAttribute(Attribute.USER_ID, user.getId());
            session.setAttribute(Attribute.INITIALS, Attribute.ADMIN_RU);

            log.info("Admin logged. User id - " + user.getId());

            response.sendRedirect(TextUtil.getRedirectPath(session, context + REDIRECT));
        } else {
            session.setAttribute(Attribute.USER_ID, user.getId());
            String initials = ServiceFactory.getInstance().getUserInfoService().getUserInitials(user.getId());
            if(initials == null){
                session.setAttribute(Attribute.ROLE, Attribute.UNREGISTERED_ROLE);
                log.info("User without account logged. User id - " + user.getId());
                response.sendRedirect(TextUtil.concat(context, CONTROLLER, REGISTER_PAGE));
            } else {
                session.setAttribute(Attribute.ROLE, Attribute.USER_ROLE);
                session.setAttribute(Attribute.INITIALS, initials);
                log.info("User logged. User id - " + user.getId());
                response.sendRedirect(TextUtil.getRedirectPath(session, context + REDIRECT));
            }
        }
    }

    private void toFailLoginPage(HttpSession session, String email, HttpServletResponse response,
                                 String context) throws IOException {
        session.setAttribute(Attribute.ROLE, Attribute.GUEST_ROLE);
        CookieHelper.addCookie(Attribute.EMAIL, email, MAX_COOKIE_ALIVE_TIME, response);
        response.sendRedirect(TextUtil.concat(context, CONTROLLER, FAIL_LOGIN_PAGE));
    }
}
