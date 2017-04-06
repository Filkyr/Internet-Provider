package by.bsuir.util.injection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public interface Injectable {

    default void inject(String configuration){
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(configuration);
//        ctx.registerShutdownHook();

        for(Field field : this.getClass().getDeclaredFields()){
            Inject annotation = field.getAnnotation(Inject.class);
            if(annotation != null){
                field.setAccessible(true);
                String beanId = annotation.beanId();
                Object bean = ctx.getBean(beanId);

                if(bean == null){
                    throw new InjectionException("There is no bean with name - " + beanId);
                }
                ReflectionUtils.setField(field, this, bean);
            }
        }
    }
}
