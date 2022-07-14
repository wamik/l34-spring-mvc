package org.example.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;

public class MainWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("----onStartup");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringWebConfig.class);


        // настройка multipart (для загрузки фото)
        int maxUploadSizeInMb = 5 * 1024 * 1024; // максимальный размер 5 MB. Если пользователь загрузит файл большего размера, будет исключение
        File uploadDir = new File(System.getProperty("java.io.tmpdir"));
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                uploadDir.getAbsolutePath(),
                maxUploadSizeInMb,
                maxUploadSizeInMb * 2,
                maxUploadSizeInMb / 2
        );


        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("halyk-dispatch", dispatcherServlet);

        registration.setMultipartConfig(multipartConfigElement);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

    }
}
