package nl.infosupport.javaminor.case1.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(AppConfig.class);
    rootContext.setServletContext(servletContext);

    ServletRegistration.Dynamic servlet = servletContext
        .addServlet("dispatcher", new DispatcherServlet(rootContext));

    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
    servlet.setMultipartConfig(getMultipartConfigElement());
  }

  private MultipartConfigElement getMultipartConfigElement() {
    String locationToSaveFile = null;
    long fileSizeOf5Mb = 5_242_880;
    long requestSizeOf20Mb = 20_971_520;
    int fileSizeThreshold = 0; // Size threshold after which files will be written to disk

    MultipartConfigElement multipartConfigElement = new MultipartConfigElement(locationToSaveFile,
        fileSizeOf5Mb, requestSizeOf20Mb, fileSizeThreshold);

    return multipartConfigElement;
  }

}
