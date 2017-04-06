package by.bsuir.command.impl.redirect;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.service.util.Validator;
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

@Log4j
@Cmd(name = "welcome-page", roles = {Role.ALL})
public class WelcomePageCmd implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(Attribute.LAST_QUERY, TextUtil.getQueryString(request));
        request.setAttribute(Attribute.EMAIL_PATTERN, Validator.EMAIL_PATTERN);
        request.setAttribute(Attribute.PASSWORD_PATTERN, Validator.PASSWORD_PATTERN);
        request.getRequestDispatcher(PagePath.WELCOME_PAGE).forward(request, response);
    }
}
