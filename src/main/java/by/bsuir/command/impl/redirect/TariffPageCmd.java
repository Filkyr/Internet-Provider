package by.bsuir.command.impl.redirect;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.entity.bean.Category;
import by.bsuir.entity.bean.Tariff;
import by.bsuir.service.TariffService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
import by.bsuir.service.util.Validator;
import by.bsuir.util.TextUtil;
import by.bsuir.util.command.Cmd;
import by.bsuir.util.command.Role;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Log4j
@Cmd(name = "tariff", roles = {Role.ALL})
public class TariffPageCmd implements Command {
    private static final String REDIRECT = "/Controller";
    private static final int ERROR_CODE = 500;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tariffId = request.getParameter(Attribute.TARIFF_ID);

        try {
            TariffService service = ServiceFactory.getInstance().getTariffService();

            Object id = session.getAttribute(Attribute.USER_ID);
            int userId = (id == null) ? -1 : (int) id;

            Tariff tariff = service.getTariff(userId, tariffId);
            List<Category> categories = ServiceFactory.getInstance().getCategoryService().getCategories(userId);

            request.setAttribute(Attribute.TARIFF, tariff);
            request.setAttribute(Attribute.CATEGORIES, categories);

            request.setAttribute(Attribute.SUM_PATTERN, Validator.SUM_PATTERN);
            request.setAttribute(Attribute.DESCRIPTION_PATTERN, Validator.DESCRIPTION_PATTERN);
            request.setAttribute(Attribute.FEATURE_PATTERN, Validator.FEATURE_PATTERN);
            request.setAttribute(Attribute.EMAIL_PATTERN, Validator.EMAIL_PATTERN);
            request.setAttribute(Attribute.PASSWORD_PATTERN, Validator.PASSWORD_PATTERN);
            session.setAttribute(Attribute.LAST_QUERY, TextUtil.getQueryString(request));
            request.getRequestDispatcher(PagePath.TARIFF_PAGE).forward(request, response);
        } catch(ServiceDataException e){
            response.sendRedirect(TextUtil.getRedirectPath(session, request.getContextPath() + REDIRECT));
        } catch(ServiceException e){
            log.error(e);
            response.sendError(ERROR_CODE);
        }
    }
}
