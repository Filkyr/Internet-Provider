package by.bsuir.command.impl.redirect;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.entity.bean.Category;
import by.bsuir.entity.bean.Tariff;
import by.bsuir.service.TariffService;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
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
@Cmd(name = "my-tariffs", roles = {Role.USER})
public class MyTariffsCmd implements Command {
    private static final int ERROR_CODE = 500;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            TariffService service = ServiceFactory.getInstance().getTariffService();

            int userId = (int) session.getAttribute(Attribute.USER_ID);

            List<Tariff> tariffs = service.getUserTariffs(userId);
            List<Category> categories = ServiceFactory.getInstance().getCategoryService().getCategories(userId);

            request.setAttribute(Attribute.TARIFFS, tariffs);
            request.setAttribute(Attribute.CATEGORIES, categories);

            session.setAttribute(Attribute.LAST_QUERY, TextUtil.getQueryString(request));
            request.getRequestDispatcher(PagePath.TARIFFS_PAGE).forward(request, response);
        } catch(ServiceException e){
            log.error(e);
            response.sendError(ERROR_CODE);
        }
    }
}
