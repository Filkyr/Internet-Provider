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

@Cmd(name = "register-page", roles = {Role.UNREGISTERED})
public class RegisterPageCmd implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieHelper.checkCookie(request, response, Attribute.REGISTER_ERROR);

        request.setAttribute(Attribute.INITIAL_PATTERN, Validator.INITIALS_RU_PATTERN);
        request.setAttribute(Attribute.MIDDLE_NAME_PATTERN, Validator.MIDDLE_NAME_JSP_PATTERN);
        request.setAttribute(Attribute.MOBILE_PHONE_PATTERN, Validator.MOBILE_PHONE_PATTERN);
        request.getRequestDispatcher(PagePath.REGISTER_PAGE).forward(request, response);
    }
}
