//package com.nic.ev.DatabaseConfig;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.nic.ev.model.UserLogin;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories()
//public class LocalDbConfiguration {
//
//	@Primary
//	@Bean
//	@ConfigurationProperties("spring.datasourceLocal")
//	public DataSourceProperties localDsProperties() {
//		return new DataSourceProperties();
//	}
//	
//	@Primary
//	@Bean
//	public DataSource localDs(@Qualifier("localDsProperties") DataSourceProperties localDsProperties) {
//		return localDsProperties.initializeDataSourceBuilder().build();
//	}
//	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean localContainerEmFactory(@Qualifier("localDs") DataSource localDs, EntityManagerFactoryBuilder builder) {
//		return builder.dataSource(localDs).packages(UserLogin.class).build();
//	}
//	
//	@Primary
//	@Bean
//	public PlatformTransactionManager localDsTransactionManager(EntityManagerFactory factory) {
//		return new JpaTransactionManager(factory);
//	}
//}
