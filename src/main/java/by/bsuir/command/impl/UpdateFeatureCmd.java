package by.bsuir.command.impl;

import by.bsuir.command.Command;
import by.bsuir.command.constant.Attribute;
import by.bsuir.entity.dto.ServerAnswer;
import by.bsuir.service.TariffService;
import by.bsuir.service.exception.ServiceDataException;
import by.bsuir.service.exception.ServiceException;
import by.bsuir.service.factory.ServiceFactory;
import by.bsuir.util.TextUtil;
import by.bsuir.util.command.Cmd;
import by.bsuir.util.command.Method;
import by.bsuir.util.command.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@Cmd(name = "update-feature", method = Method.POST, roles = {Role.ADMIN})
public class UpdateFeatureCmd implements Command {
    private static final String CONTENT_TYPE = "application/json";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService service = ServiceFactory.getInstance().getTariffService();
        ServerAnswer<String> answer = new ServerAnswer<>();
        answer.setError(TextUtil.FALSE);

        try {
            String feature = request.getParameter(Attribute.FEATURE);
            String featureId = request.getParameter(Attribute.FEATURE_ID);

            service.updateFeature(featureId, feature);
//            answer.setContent(TextUtil.getSubscriptionAlert(request.getParameter(Attribute.NAME), newSub));
        } catch(ServiceException | ServiceDataException e){
            answer.setError(TextUtil.TRUE);
        }

        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(answer));
    }
}
