package com.forgetPassword.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.activation.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by ravi on 4/5/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.forgetPassword")
@EnableTransactionManagement
@Import({SecurityConfig.class})
public class RootApplicationContextConfig {
    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto","create-drop");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.format_sql","true");
        return properties;
    }

    @Bean(name = "dataSource")
    public javax.sql.DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://localhost:3306/forgetpwd");
        return dataSource;
    }
/*
    @Bean
    public DataSourceInitializer dataSourceInitializer(){
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
       // resourceDatabasePopulator.addScript(new ClassPathResource(''));
        resourceDatabasePopulator.addScript(new ClassPathResource("db/insert-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }*/

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setHibernateProperties(getHibernateProperties());
        bean.setPackagesToScan("com.forgetPassword");
        return bean;
    }

    @Bean(name = "hibernateTemplate")
    public HibernateTemplate getHibernateTemplate(){
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject());
        return hibernateTemplate;
    }

    @Bean(name = "transactionManager")
    @DependsOn("sessionFactory")
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate transactionTemplate() throws PropertyVetoException{
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        return transactionTemplate;
    }
}
