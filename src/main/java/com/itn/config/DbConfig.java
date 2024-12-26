package com.itn.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DbConfig {
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");		
		ds.setUrl("jdbc:mysql://localhost:3306/website");
		ds.setUsername("root");
		ds.setPassword("admin@123#");
		return ds;
	}
	
	public Properties getHibernateProperties() {
		Properties props=new Properties();
		props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.hbm2ddl.auto","update");
		props.put("hibernate.format_sql","true");
		props.put("hibernate.show_sql","true");
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		sessionFactoryBean.setPackagesToScan("com.itn.entity");
		
		return sessionFactoryBean;

	}
	
	@Bean
	public PlatformTransactionManager getTransactioonManager() {
		HibernateTransactionManager tm=new HibernateTransactionManager();
		tm.setSessionFactory(getSessionFactoryBean().getObject());
		return tm;
		
	}

}
