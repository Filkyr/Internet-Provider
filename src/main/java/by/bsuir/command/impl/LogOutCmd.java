package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
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
@Cmd(name = "log-out", method = Method.POST, roles = {Role.ALL})
public class LogOutCmd implements Command {
    private static final String REDIRECT = "/Controller?cmd=welcome-page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            log.info("User logged out. User id - " + session.getAttribute(Attribute.USER_ID));
            session.invalidate();
        }

        response.sendRedirect(TextUtil.concat(request.getContextPath(), REDIRECT));
    }
}
