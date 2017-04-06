package by.bsuir.command.impl.redirect;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.service.util.Validator;
import by.bsuir.util.CookieHelper;
import by.bsuir.util.command.Cmd;
import by.bsuir.util.command.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Cmd(name = "fail-login-page", roles = {Role.GUEST})
public class FailLoginPageCmd implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieHelper.findInCookie(request, Attribute.EMAIL);
        CookieHelper.findInCookie(request, Attribute.BUSY_EMAIL_ERROR);
        CookieHelper.findInCookie(request, Attribute.SIGN_UP_DATA_ERROR);

        request.setAttribute(Attribute.EMAIL_PATTERN, Validator.EMAIL_PATTERN);
        request.setAttribute(Attribute.PASSWORD_PATTERN, Validator.PASSWORD_PATTERN);
        request.getRequestDispatcher(PagePath.LOGIN_FAIL_PAGE).forward(request, response);
    }
}
