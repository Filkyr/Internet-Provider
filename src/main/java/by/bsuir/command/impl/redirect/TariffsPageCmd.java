package by.bsuir.command.impl.redirect;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.entity.bean.Category;
import by.bsuir.entity.bean.Tariff;
import by.bsuir.service.TariffService;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
import by.bsuir.service.util.Validator;
import by.bsuir.util.CookieHelper;
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
@Cmd(name = "tariffs", roles = {Role.ALL})
public class TariffsPageCmd implements Command {
    private static final int ERROR_CODE = 500;
    private static final int DEFAULT_CATEGORY = 1;
    private static final int DEFAULT_PAGE = 1;
    private static final double DEFAULT_TARIFF_AMOUNT = 3D;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        CookieHelper.checkCookie(request, response, Attribute.ADD_TARIFF_ERROR);

        try {
            int categoryId = Validator.checkAndGetNumber(request.getParameter(Attribute.CATEGORY_ID), DEFAULT_CATEGORY);

            TariffService service = ServiceFactory.getInstance().getTariffService();
            int allAccounts = service.getTariffsAmount(categoryId);
            int totalPage = (int) Math.ceil(allAccounts / DEFAULT_TARIFF_AMOUNT);
            int currentPage = this.getCurrentPage(request, totalPage);

            int start = (int) ((currentPage - 1) * DEFAULT_TARIFF_AMOUNT);
            int amount = (int) DEFAULT_TARIFF_AMOUNT;

            Object id = session.getAttribute(Attribute.USER_ID);
            int userId = (id == null) ? -1 : (int) id;

            List<Tariff> tariffs = service.getTariffs(userId, categoryId, start, amount);
            List<Category> categories = ServiceFactory.getInstance().getCategoryService().getCategories(userId);

            request.setAttribute(Attribute.TARIFFS, tariffs);
            request.setAttribute(Attribute.CATEGORIES, categories);
            request.setAttribute(Attribute.CURRENT_PAGE, currentPage);
            request.setAttribute(Attribute.TOTAL_PAGES, totalPage);

            request.setAttribute(Attribute.SUM_PATTERN, Validator.SUM_PATTERN);
            request.setAttribute(Attribute.NAME_PATTERN, Validator.TARIFF_NAME_PATTERN);
            request.setAttribute(Attribute.DESCRIPTION_PATTERN, Validator.DESCRIPTION_PATTERN);
            request.setAttribute(Attribute.FEATURE_PATTERN, Validator.FEATURE_PATTERN);
            request.setAttribute(Attribute.EMAIL_PATTERN, Validator.EMAIL_PATTERN);
            request.setAttribute(Attribute.PASSWORD_PATTERN, Validator.PASSWORD_PATTERN);
            session.setAttribute(Attribute.LAST_QUERY, TextUtil.getQueryString(request));
            request.getRequestDispatcher(PagePath.TARIFFS_PAGE).forward(request, response);
        } catch(ServiceException e){
            log.error(e);
            response.sendError(ERROR_CODE);
        }
    }

    private int getCurrentPage(HttpServletRequest request, int totalPage) {
        int currentPage = Validator.checkAndGetNumber(request.getParameter(Attribute.PAGE), DEFAULT_PAGE);
        currentPage = (currentPage > totalPage) ? totalPage : currentPage;
        currentPage = (currentPage == 0) ? 1 : currentPage;
        return currentPage;
    }
}
