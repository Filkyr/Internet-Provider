package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.service.TariffService;
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

@Log4j
@Cmd(name = "insert-usage", method = Method.POST, roles = {Role.USER, Role.ADMIN})
public class InsertUsageCmd implements Command {
    private static final String REDIRECT = "/Controller";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String context = request.getContextPath();

        TariffService service = ServiceFactory.getInstance().getTariffService();
        int userId = (int) session.getAttribute(Attribute.USER_ID);
        String tariffId = request.getParameter(Attribute.TARIFF_ID);

        try {
            service.insertUsage(userId, tariffId);
//            log.info("Admin has created a new tariff. Tariff id - " + tariffId);
        } catch(ServiceException | ServiceDataException e){
//            log.warn("While adding tariff error occurred", e);
            CookieHelper.addCookie(Attribute.INSERT_USAGE_ERROR, Attribute.OCCURRED, CookieHelper.UNDEFINED_AGE, response);
        }

        response.sendRedirect(TextUtil.getRedirectPath(session, context + REDIRECT));
    }
}
