package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.entity.bean.User;
import by.bsuir.service.UserService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
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
@Cmd(name = "sign-up", method = Method.POST, roles = {Role.GUEST, Role.NULL})
public class SignUpCmd implements Command {
    private static final String CONTROLLER = "/Controller?cmd=";
    private static final String REGISTER_PAGE = "register-page";
    private static final String FAIL_LOGIN_PAGE = "fail-login-page";
    private static final int MAX_COOKIE_ALIVE_TIME = 60;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String context = request.getContextPath();

        String email = request.getParameter(Attribute.EMAIL);
        byte[] password = request.getParameter(Attribute.PASSWORD).getBytes(StandardCharsets.UTF_8);

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            User user = service.getByEmail(email);
            if(user == null){
                int userId = service.createUser(email, password);
                session.setAttribute(Attribute.ROLE, Attribute.UNREGISTERED_ROLE);
                session.setAttribute(Attribute.USER_ID, userId);

                log.info("New user has registered. User id - " + userId);

                response.sendRedirect(TextUtil.concat(context, CONTROLLER, REGISTER_PAGE));
            } else {
                this.redirectToFailLoginPage(Attribute.BUSY_EMAIL_ERROR, session, context, response);
            }
        } catch(ServiceException e){
            log.error(e);
            session.setAttribute(Attribute.ROLE, Attribute.GUEST_ROLE);
            response.sendRedirect(TextUtil.concat(context, TextUtil.SLASH, PagePath.PATH_TO_500_PAGE));
        } catch(ServiceDataException e){
            log.warn("While signing up error occurred", e);
            this.redirectToFailLoginPage(Attribute.SIGN_UP_DATA_ERROR, session, context, response);
        }
    }

    private void redirectToFailLoginPage(String cookieKey, HttpSession session, String context,
                                         HttpServletResponse response) throws IOException {
        session.setAttribute(Attribute.ROLE, Attribute.GUEST_ROLE);
        CookieHelper.addCookie(cookieKey, Attribute.OCCURRED, MAX_COOKIE_ALIVE_TIME, response);
        response.sendRedirect(TextUtil.concat(context, CONTROLLER, FAIL_LOGIN_PAGE));
    }
}
