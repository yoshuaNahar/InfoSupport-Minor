package nl.infosupport.javaminor.case1.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "nl.infosupport.javaminor.case1")
public class AppConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

  @Autowired
  private Environment env;

  // Thymeleaf
  private ApplicationContext applicationContext;

  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUsername(env.getProperty("db.user"));
    dataSource.setPassword(env.getProperty("db.password"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setDriverClassName(env.getProperty("db.driver"));

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("nl.infosupport.javaminor.case1.entities");
    factory.setDataSource(dataSource());

    Map<String, Object> jpaProperties = new HashMap<>();
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
    jpaProperties.put("hibernate.show_sql", true);
    jpaProperties.put("hibernate.format_sql", true);
    jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
    factory.setJpaPropertyMap(jpaProperties);

    return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return txManager;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    int oneYear = 31_556_926;
    registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/WEB-INF/resources/")
        .setCachePeriod(oneYear);

    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/")
        .setCachePeriod(oneYear);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  // Print files http://www.baeldung.com/spring-file-upload
  @Bean
  public StandardServletMultipartResolver resolver() {
    return new StandardServletMultipartResolver();
  }

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(100_000);

    return new CommonsMultipartResolver();
  }

  // Thymeleaf
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");

    return resolver;
  }

  @Bean
  public ITemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setEnableSpringELCompiler(true);
    engine.setTemplateResolver(templateResolver());

    return engine;
  }

  @Bean
  public ITemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/WEB-INF/templates/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCacheable(false); // for hotswapping

    return resolver;
  }

}
