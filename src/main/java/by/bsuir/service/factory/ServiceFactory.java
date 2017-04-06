package by.bsuir.service.factory;

import by.bsuir.service.CategoryService;
import by.bsuir.service.MailService;
import by.bsuir.service.SourceService;
import by.bsuir.service.TariffService;
import by.bsuir.service.UserInfoService;
import by.bsuir.service.UserService;
import by.bsuir.util.injection.Inject;
import by.bsuir.util.injection.Injectable;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ServiceFactory implements Injectable {
    private static final String SERVICE_CONFIG = "/bean/service.xml";
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    @Inject(beanId = "source")
    private SourceService sourceService;

    @Inject(beanId = "user")
    private UserService userService;

    @Inject(beanId = "userInfo")
    private UserInfoService userInfoService;

    @Inject(beanId = "tariff")
    private TariffService tariffService;

    @Inject(beanId = "category")
    private CategoryService categoryService;

    @Inject(beanId = "mail")
    private MailService mailService;

    private ServiceFactory(){
        this.inject(SERVICE_CONFIG);
    }

    public static ServiceFactory getInstance(){
        return INSTANCE;
    }
}
