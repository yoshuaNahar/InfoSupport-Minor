package jpa.context;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.DefaultJpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "jpa")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class Config {

  @Autowired
  private Environment env;

  @Bean
  public BasicDataSource basicDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUsername(env.getProperty("db.user"));
    dataSource.setPassword(env.getProperty("db.password"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setDriverClassName(env.getProperty("db.driver"));
    return dataSource;
  }

  // Read up on the link below as to why I created my emf this way
  // http://docs.spring.io/spring/docs/4.3.8.RELEASE/spring-framework-reference/htmlsingle/#orm-jpa-setup-lcemfb
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

    // With some additional configuration you can enable JDBC to use JPA transactions.
    // You’ll need to configure a JpaDialect for this to work. The full configuration is as follows.
    // This is used to use JdbcTemplate
    DefaultJpaDialect jpaDialect = new HibernateJpaDialect();

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("jpa.domain"); // location of your entities
    factory.setDataSource(basicDataSource());
    factory.setJpaDialect(jpaDialect);

    Map<String, Object> jpaProperties = new HashMap<>();
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    jpaProperties.put("hibernate.show_sql", true);
    jpaProperties.put("hibernate.format_sql", true);
    jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
    factory.setJpaPropertyMap(jpaProperties);

    return factory;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(basicDataSource());
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return txManager;
  }

}
