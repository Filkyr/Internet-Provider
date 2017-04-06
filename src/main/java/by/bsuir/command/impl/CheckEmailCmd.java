package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.service.UserService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
import by.bsuir.util.TextUtil;
import by.bsuir.util.command.Cmd;
import by.bsuir.util.command.Method;
import by.bsuir.util.command.Role;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@Cmd(name = "check-email", method = Method.POST, roles = {Role.ALL})
public class CheckEmailCmd implements Command {
    private static final String CONTENT_TYPE= "text/plain";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Attribute.EMAIL);
        String answer = TextUtil.TRUE;

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            if(!service.isEmailFree(email)){
                answer = TextUtil.FALSE;
            }
        } catch(ServiceException | ServiceDataException e){
            log.warn("While checking email error occurred. Email - " + email, e);
            answer = TextUtil.FALSE;
        }

        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(answer);
    }
}
