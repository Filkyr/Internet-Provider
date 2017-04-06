package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.command.constant.PagePath;
import by.bsuir.entity.bean.UserInfo;
import by.bsuir.service.UserInfoService;
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
@Cmd(name = "register", method = Method.POST, roles = {Role.UNREGISTERED})
public class RegisterCmd implements Command {
    private static final String CONTROLLER = "/Controller?cmd=";
    private static final String REDIRECT = "/Controller?cmd=welcome-page";
    private static final String REGISTER_PAGE = "register-page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String context = request.getContextPath();

        UserInfo userInfo = this.makeUserInfo(request);

        UserInfoService userInfoService = ServiceFactory.getInstance().getUserInfoService();

        try {
            int userId = (int) session.getAttribute(Attribute.USER_ID);
            userInfo.setUserId(userId);
            userInfoService.addUserInfo(userInfo);

            session.setAttribute(Attribute.INITIALS, userInfoService.getUserInitials(userId));
            session.setAttribute(Attribute.ROLE, Attribute.USER_ROLE);
            response.sendRedirect(context + REDIRECT);
        } catch(ServiceException e){
            log.error(e);
            response.sendRedirect(TextUtil.concat(context, TextUtil.SLASH, PagePath.PATH_TO_500_PAGE));
        } catch(ServiceDataException e){
            log.warn("While registering error occurred", e);
            CookieHelper.addCookie(Attribute.REGISTER_ERROR, Attribute.OCCURRED, CookieHelper.UNDEFINED_AGE, response);
            response.sendRedirect(TextUtil.concat(context, CONTROLLER, REGISTER_PAGE));
        }
    }

    private UserInfo makeUserInfo(HttpServletRequest request){
        UserInfo user = new UserInfo();
        user.setSurname(request.getParameter(Attribute.SURNAME));
        user.setName(request.getParameter(Attribute.NAME));
        user.setMobilePhone(request.getParameter(Attribute.MOBILE_PHONE));
        String lastName = request.getParameter(Attribute.LAST_NAME);
        if(lastName.isEmpty()){
            lastName = null;
        }
        user.setLastName(lastName);

        return user;
    }
}
