package by.bsuir.service.impl;

import by.bsuir.dao.factory.DAOFactory;
import by.bsuir.service.SourceService;
import org.springframework.stereotype.Service;

@Service("source")
public class SourceServiceImpl implements SourceService {

    @Override
    public void init() {
        DAOFactory.getInstance().getSourceInit().init();
    }

    @Override
    public void destroy() {
        DAOFactory.getInstance().getSourceInit().destroy();
    }
}
